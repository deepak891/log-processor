# Executable jar path
    <project_root>/log_processor_executable/log-process.jar

# Running the application
    - Please enter below command to run the application
    ```
    java -jar log-processor.jar -f logfile.csv -d 2018-12-09
    ```

#Project structure
    - Main Class : LogProcessorMain - Responsible for wiring loader, converter, processor and service.
    - Converter : responsible to convert raw message to specific message
    - loader : responsible to load file and post message to queue
    - model : model class are here
    - processor : responsible for processing the message
    - service : responsible for doing business logic
    - utils : container helper class