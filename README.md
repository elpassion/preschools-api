# preschools-api
api for preschools app

### Requirements

* Java 1.8 or greater
* Maven (`brew install maven` on OSX) 

All configuration is stored in `settings.sh`

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

### Origins
This application was created during ElPassion's Hackathon on 26-27.11.2015


