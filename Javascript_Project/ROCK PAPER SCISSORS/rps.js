let userScore = 0;
let compScore = 0;

const choices = document.querySelectorAll(".symbol");
const you = document.querySelector("#yourScore");
const computer = document.querySelector("#compScore");
const msg = document.querySelector("#msg");

const genCompChoice = () =>{
    const choiceArr = ["rock", "paper", "scissors"];
    const compIdx = Math.floor(Math.random() * 3);
    return choiceArr[compIdx];
};

const showWinner = (userWin, userChoice, compChoice) => {
    if(userWin){
        userScore += 1;
        you.innerText = userScore;
        msg.innerText = `YOU WIN.! Your ${userChoice} beats ${compChoice}`;
        msg.style.backgroundColor = "green";
    }
    else{
        compScore += 1;
        computer.innerText = compScore;
        msg.innerText = `YOU LOSE.! ${compChoice} beats your ${userChoice}`;
        msg.style.backgroundColor = "red";
    }
};

const playGame = (userChoice) => {
    const compChoice = genCompChoice();
    if(userChoice === compChoice){
        msg.innerText = "GAME DRAW..!!";
        msg.style.backgroundColor = "black";
    }
    else{
        let userWin = true;
        if(userChoice === "rock"){
            userWin = compChoice === "paper" ? false : true;
        }
        else if(userChoice === "paper"){
            userWin = compChoice === "scissors" ? false : true;
        }
        else{
            userWin = compChoice === "rock" ? false : true;
        }
        showWinner(userWin, userChoice, compChoice);
    }
};

choices.forEach((choice) => {
    choice.addEventListener("click", () => {
        const userChoice = choice.getAttribute("id");
        playGame(userChoice);
    });
});