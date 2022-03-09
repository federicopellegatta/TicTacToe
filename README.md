# TicTacToe

TicTacToe is simple tic-tac-toe game developed using Spring. 

## Installation

### Dependencies
 - [Node](https://nodejs.org/it/download/) (>=16.0.0)
 - [Docker](https://docs.docker.com/get-docker/) (>=20.10.0)

### Downloading and building
If all the dependencies have been met, you can clone and then build the project:
```sh
  git clone https://github.com/federicopellegatta/TicTacToe.git
```
 - Create a docker container:
    ```sh
    sudo docker run -it --name containerName -e MYSQL_ROOT_PASSWORD=yourPW -p 3306:3306 mysql
    ```
    In order to start/stop the container
    ```sh
    sudo docker start containerName
    sudo docker stop containerName
    ```
    
 - Run the TicTacToe frontend in your own directory:
    ```sh
    cd path/to/your/directory
    cd frontend
    npm install
    npm run start
    ```

## Licence
TicTacToe is licensed under the terms of the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html) and is available for free. See the file [LICENCE.md](https://github.com/federicopellegatta/TicTacToe/blob/master/LICENCE.md).

