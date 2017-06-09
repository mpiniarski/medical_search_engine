# MedSort - simple medical articles search engine

![zrzut ekranu w 2017-06-09 14-58-22](https://user-images.githubusercontent.com/12546644/26976542-85f6cd74-4d24-11e7-93c0-78c758afdf5a.png)

Uses Pubmed (https://www.ncbi.nlm.nih.gov/pubmed/) article base. You can download part of it in xml format (Choose 'Send to' -> 'File' option on Search result screen)

## Download and build ##
1. Clone repository

    ```
    git clone https://github.com/mpiniarski/medical_search_engine.git
    ```
2. Change directory

    ```
    cd medical_search_engine/backend
    ```
3. Build 

    ```
    ./gradlew build
    ```

## Import ##
4. Import part of Pubmed base in xml format by launching:

    ```
    java -jar import/build/libs/import-all.jar -i /path/to/index/directory -x /path/to/xml/file
    ```

## Start server ##
5. Copy and fill property file

    ```
    cp ./server/application.properties.dist ./server/application.properties
    vim ./server/application.properties
    ```

6. Start server 

    ```
    java -jar server/build/libs/server.jar
    
    ```

## Start frontend ##
7. Change directory:
    ```
    cd ../client
    ```
8. Run commands:
    ```
    npm install
    npm start
    ```
## Use ##
9. Go to localhost:4200
