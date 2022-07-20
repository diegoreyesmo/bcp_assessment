### Maven install
```bash
$ mvn clean install
```

### Docker Build and Docker Run Images
```bash
$ docker build . -t bcp_assessment
$ docker run -d --name bcp_assessment-local -p 5000:5000 --env API_CURRENCY_URL=http://172.17.0.2:8080/fixer/latest bcp_assessment
```
