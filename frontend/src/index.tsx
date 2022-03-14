import React from 'react';
import ReactDOM from 'react-dom';
import axios from "axios";
import {Button, Chip} from "@mui/material";

enum Player { X = 'X', O = 'O'}

enum CellStatus { X = 'X', O = 'O', E = 'E'}

const createNewGame = () => axios.get('http://localhost:8080/newGame').then(r => r.data);

const makeMove = (i: number, j: number) =>
    axios.post(`http://localhost:8080/makeMove?i=${i}&j=${j}`).then(r => r.data);

type Move = {
    currentPlayer: Player,
    gameTable: CellStatus[][],
    winner: Player | null
};

const TicTacToeApp = () => {

    const [move, setMove] = React.useState<Move | null>(null);

    React.useEffect(() => void createNewGame().then(setMove), []);

    const Square = ({cell, row, col}: { cell: CellStatus, row: number, col: number }) => {
        return <Button onClick={() => cell === CellStatus.E && makeMove(row, col).then(setMove)}
                       color={cell === CellStatus.E ? 'primary' : 'success'}
                       style={{width: '80px', height: '80px', fontSize: "20px"}}
                       variant="contained">
            {cell === CellStatus.E ? '-' : cell === CellStatus.X ? 'X' : 'O'}
        </Button>
    }

    const CurrentPlayer = ({currentPlayer}: { currentPlayer: Player }) => <>
        <Chip label={currentPlayer === Player.X ? '   X   ' : '   O   '}
              color={currentPlayer === Player.X ? 'error' : 'success'}
              variant="outlined"
              style={{margin: '20px', fontSize: "30px"}}/>
    </>

    if (!move) return <div>Loading...</div>;

    return <div style={{textAlign: "center"}}>
        <h1>Play Tic-Tac-Toe!</h1>
        <div>
            <Square cell={move.gameTable[0][0]} row={0} col={0}/>
            <Square cell={move.gameTable[0][1]} row={0} col={1}/>
            <Square cell={move.gameTable[0][2]} row={0} col={2}/>
        </div>
        <div>
            <Square cell={move.gameTable[1][0]} row={1} col={0}/>
            <Square cell={move.gameTable[1][1]} row={1} col={1}/>
            <Square cell={move.gameTable[1][2]} row={1} col={2}/>
        </div>
        <div>
            <Square cell={move.gameTable[2][0]} row={2} col={0}/>
            <Square cell={move.gameTable[2][1]} row={2} col={1}/>
            <Square cell={move.gameTable[2][2]} row={2} col={2}/>
        </div>

        <div>
            <Button onClick={() => createNewGame().then(setMove)}
                    color="primary"
                    variant="contained"
                    style={{margin: '20px'}}>
                Start a new game
            </Button>
        </div>

        <div>
            <CurrentPlayer currentPlayer={move.currentPlayer}/>
        </div>

    </div>

};

ReactDOM.render(
    <React.StrictMode>
        <TicTacToeApp/>
    </React.StrictMode>,
    document.getElementById('root')
);