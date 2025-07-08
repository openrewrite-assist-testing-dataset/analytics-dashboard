# Analytics Dashboard API

A multi-module Dropwizard application for analytics data collection, processing, and dashboard reporting.

## Overview

This service provides comprehensive analytics capabilities with:
- **dashboard-api**: REST API for analytics queries and dashboard data
- **data-aggregator**: Background service for data collection and aggregation
- **report-generator**: Service for generating reports and exports
- **common**: Shared models and utilities

## Technology Stack

- **Framework**: Dropwizard 2.1.x
- **Java**: Java 14
- **Build Tool**: Gradle 7.6
- **Database**: PostgreSQL with JDBI
- **Time Series**: InfluxDB for metrics storage
- **Authentication**: JWT + API Key
- **Reporting**: JasperReports for PDF generation
- **Scheduling**: Quartz scheduler
- **Logging**: Log4j2
- **Testing**: JUnit 5

## Features

### Analytics Capabilities
- Real-time metrics collection
- Historical data analysis
- Custom dashboard creation
- Interactive charts and graphs
- KPI monitoring and alerting
- Data export (CSV, PDF, Excel)

### Supported Metrics
- User engagement metrics
- Performance monitoring
- Business KPIs
- Custom event tracking
- System health metrics
- Revenue analytics

### Dashboard Features
- Customizable dashboards
- Real-time updates
- Interactive filtering
- Drill-down capabilities
- Multi-tenant support
- Role-based access control

## API Endpoints

### Authentication
- JWT Token: `Authorization: Bearer <token>`
- API Key: `X-API-Key: <key>`

### Metrics API

#### Submit Metrics
```bash
POST /api/v1/metrics
Content-Type: application/json

{
  "name": "user_signup",
  "value": 1,
  "timestamp": "2023-01-01T12:00:00Z",
  "tags": {
    "source": "web",
    "campaign": "winter2023"
  }
}
```

#### Query Metrics
```bash
GET /api/v1/metrics?name=user_signup&from=2023-01-01&to=2023-01-31&groupBy=day
```

### Dashboard API

#### Get Dashboard
```bash
GET /api/v1/dashboards/{dashboardId}
```

#### Create Dashboard
```bash
POST /api/v1/dashboards
Content-Type: application/json

{
  "name": "User Analytics",
  "description": "User engagement metrics",
  "widgets": [
    {
      "type": "line_chart",
      "query": "user_signup",
      "timeRange": "7d"
    }
  ]
}
```

### Reports API

#### Generate Report
```bash
POST /api/v1/reports/generate
Content-Type: application/json

{
  "type": "PDF",
  "template": "monthly_report",
  "parameters": {
    "month": "2023-01",
    "includeCharts": true
  }
}
```

#### Export Data
```bash
GET /api/v1/reports/export?format=csv&metrics=user_signup,revenue&from=2023-01-01
```

## Setup Instructions

### Prerequisites
- Java 14
- PostgreSQL
- InfluxDB
- Gradle 7.6

### Database Setup
1. Create PostgreSQL database:
```sql
CREATE DATABASE analytics_dashboard;
CREATE USER analytics_user WITH PASSWORD 'analytics_pass';
GRANT ALL PRIVILEGES ON DATABASE analytics_dashboard TO analytics_user;
```

2. Set up InfluxDB:
```bash
influx setup --username admin --password admin123 --org analytics --bucket metrics
```

### Running the Services

1. Build the project:
```bash
./gradlew build
```

2. Run the dashboard API:
```bash
./gradlew :dashboard-api:run
```

3. Run the data aggregator:
```bash
./gradlew :data-aggregator:run
```

4. Run the report generator:
```bash
./gradlew :report-generator:run
```

## Configuration

```yaml
database:
  url: jdbc:postgresql://localhost:5432/analytics_dashboard
  user: analytics_user
  password: analytics_pass

influxdb:
  url: http://localhost:8086
  token: your-influxdb-token
  org: analytics
  bucket: metrics

auth:
  jwtSecret: "analytics-secret-key-2023"
  apiKeys:
    - "analytics-api-key-001"
    - "analytics-api-key-002"
```

## Architecture

### Data Flow
1. Metrics ingested via REST API
2. Data stored in InfluxDB for time series
3. Metadata stored in PostgreSQL
4. Background aggregation processes
5. Dashboard queries and visualization
6. Report generation and export

### Modules
- **common**: Shared data models and utilities
- **dashboard-api**: REST API and web interface
- **data-aggregator**: Background data processing
- **report-generator**: Report creation and export

## Performance Features

- Time series database for efficient metrics storage
- Background data aggregation
- Caching layer for dashboard queries
- Batch processing for large datasets
- Optimized database indexes

## Security Features

- JWT-based authentication
- API key support
- Role-based access control
- Data encryption at rest
- Audit logging

## Monitoring

- Application metrics via JMX
- Health checks for all components
- Performance monitoring
- Error tracking and alerting

## Testing

Run all tests:
```bash
./gradlew test
```

## Docker Deployment

Build and run with Docker:
```bash
docker build -t analytics-dashboard .
docker-compose up -d
```

## Troubleshooting

### Common Issues

1. **InfluxDB Connection Issues**
   - Verify InfluxDB is running
   - Check authentication token
   - Validate bucket permissions

2. **Performance Issues**
   - Check database indexes
   - Monitor query execution times
   - Optimize time series queries

3. **Report Generation Failures**
   - Verify JasperReports templates
   - Check memory allocation
   - Validate data availability

## Contributing

1. Fork the repository
2. Create feature branch
3. Make changes with comprehensive tests
4. Run tests and ensure they pass
5. Submit pull request with detailed description