# preschools-api
api for preschools app

### Requirements

* Java 1.8 or greater
* Maven (`brew install maven` on OSX) 

All configuration is stored in `settings.sh`

### Endpoints

* `GET  /schools/locations` - returns full list of schools with locations
* `GET  /schools/:id` - returns single school
* `POST /schools/:id/comments` - creates comment for school, params: `nick`, `body`
* `GET  /schools/:id/comments` - returns comments for given school, pagination enabled, params: `limit`, `offset`


### Deploy to heroku

```
mvn heroku:deploy
```

### Running migrations

On local development machine

```
./migrate.sh
```

On heroku

```
heroku run migrate
```

### Fetching preschools from Warsaw

```
heroku run imports_warsaw
```

### Origins
This application was created during ElPassion's Hackathon on 26-27.11.2015


