# Running the application
    - Below command will run the application
    - java -jar .\log-processor.jar -f logfile.csv -d 2018-12-09

# Executable jar path
    <path_to_project>/log_processor_jar/log-process.jar

#Project structure
    - Main Class : LogProcessorMain - Responsible for wiring loader, converter, processor and service.
    - Converter : responsible to convert raw message to specific message
    - loader : responsible to load file and post message to queue
    - model : model class are here
    - processor : responsible for processing the message
    - service : responsible for doing business logic
    - utils : container helper class