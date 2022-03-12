import React from 'react';
import ReactDOM from 'react-dom';
import axios from "axios";
import {Button, TextField} from "@mui/material";

const GAME_BASE_URL = 'http://localhost:8080';

enum CellStatus {X = 'X', O = 'O', __ = '__'}

enum Player {X = 'X', O = 'O'}

type Game = {
    currentPlayer: Player,
    gameTable: CellStatus[][],
    winner: Player | null,
    isValid: boolean,
    isDraw: boolean,
    isGameOver: boolean
}

const getMoves = async () => {
    const result = await axios.get(GAME_BASE_URL + 'games')
    return result.data as Game[];
}

const createNewGame = async () => {
    const result = await axios.get(GAME_BASE_URL + '/game/newGame');
    return result.data as Game[];
}

const makeMove = async (i: number, j: number) => {
    await axios.post(GAME_BASE_URL + `/game/makeMove?i=${i}&j=${j}`).then(res => res.data);
}

const Game = (prop: Game) => {
    return <div>
        <h3>{prop.currentPlayer} has played and the board is:</h3>
        <p>{prop.gameTable}</p>
    </div>
};

const TicTacToeApp = () => {

    const [newI, setNewI] = React.useState(0);
    const [newJ, setNewJ] = React.useState(0);
    const [moves, setMoves] = React.useState <Game[]>([]);

    React.useEffect(() => void createNewGame().then(res => setMoves(res)), []);

    const handleInputI = (e: any) => setNewI(parseInt(e.target.value));
    const handleInputJ = (e: any) => setNewJ(parseInt(e.target.value));

    const handleAddMove = async () => {
        if (!newI || !newJ)
            return;
        await makeMove(newI, newJ);
        setNewI(0);
        setNewJ(0);
    }

    return <div>

        <TextField label="Row" type="number" InputLabelProps={{shrink: true,}}
                   value={newI} onInput={handleInputI}/>
        <TextField label="Column" type="number" InputLabelProps={{shrink: true,}}
                   value={newJ} onInput={handleInputJ}/>
        <Button variant="contained" onClick={handleAddMove}>Submit</Button>

        {moves.map((m, id) =>
            <Game key={id} currentPlayer={m.currentPlayer} gameTable={m.gameTable} winner={m.winner}
                  isValid={m.isValid} isDraw={m.isDraw} isGameOver={m.isGameOver}/>)
        }

    </div>
}

ReactDOM.render(
    <React.StrictMode>
        <h1>Play Tic-Tac-Toe!</h1>
        <TicTacToeApp/>
    </React.StrictMode>,
    document.getElementById('root')
);