## Superhero Service
* This service was written to the dataguard challenge
* It was written with Java 8 and Spring Framework

### How to run?

* docker-compose build
* docker-compose up

<p>This service is exposed in 7001 port</p>
<p>To open the swagger access the follow address in your browser: http://localhost:7001/swagger-ui.html</p>

### Exposed routes:
* GET /api
* POST /api
* PUT /api/{superHeroId}
* DELETE /api/{superHeroId}
* GET /api/filter
* GET /api/power
* GET /api/weapon

### The body to POST requests:
```
{
  "alias": "string",
  "associations": [
    "string"
  ],
  "name": "string",
  "origin": "string",
  "powers": [
    "string"
  ],
  "weapons": [
    "string"
  ]
}
```

### The response body:
```
[
  {
    "alias": "string",
    "associations": [
      "string"
    ],
    "name": "string",
    "origin": "string",
    "powers": [
      "string"
    ],
    "superHeroId": 0,
    "weapons": [
      "string"
    ]
  }
]
```

#### This application is running a H2 database just to tests
#### I wanted to add more unit tests, but I took a long time to develop only this small application