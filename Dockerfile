# Multi-stage build
FROM openjdk:11-jdk-slim as builder

WORKDIR /app

# Copy gradle files
COPY build.gradle settings.gradle ./
COPY gradle/ ./gradle/
COPY gradlew ./

# Copy source code
COPY common/ ./common/
COPY dashboard-api/ ./dashboard-api/
COPY data-aggregator/ ./data-aggregator/
COPY report-generator/ ./report-generator/

# Build the application
RUN ./gradlew clean build -x test

# Runtime stage
FROM openjdk:11-jre-slim

WORKDIR /app

# Create non-root user
RUN groupadd -r analytics && useradd -r -g analytics analytics

# Copy the built JARs
COPY --from=builder /app/dashboard-api/build/libs/dashboard-api-*.jar dashboard-api.jar

# Create logs directory
RUN mkdir -p logs && chown analytics:analytics logs

# Expose port
EXPOSE 8080 8081

# Switch to non-root user
USER analytics

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8081/healthcheck || exit 1

# Run the dashboard API
ENTRYPOINT ["java", "-jar", "dashboard-api.jar", "server", "application.yml"]