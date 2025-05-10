// all those objects which had to be dynamically changed are DOMed
let boxes = document.querySelectorAll(".box");
let resetbtn = document.querySelector("#reset_btn");
let newbtn = document.querySelector("#new_btn");
let msgcontainer = document.querySelector(".msg-container");
let msg = document.querySelector("#msg");

let turn0 = true;// denotes whether the playerX turn or player0 turn
//initially this line states that palyer0 will have its turn

let c = 0;// used to count the number of boxes filled i.e max value is 9.

// array of arrays: of winning patterns i.e. posn where similar symbol will win.
const winPatterns = [
    [0,1,2],
    [0,3,6],
    [0,4,8],
    [1,4,7],
    [2,5,8],
    [2,4,6],
    [3,4,5],
    [6,7,8],
];

const resetGame = () =>{
    turn0 = true; // reset turn
    c = 0;// reset counter
    enableBoxes();//enable all boxes
    msgcontainer.classList.add("hide");
    resetbtn.classList.remove("hide");// hide the msg container
}

const drawGame = () =>{
    msg.innerText = "GAME IS DRAW....!!"; // print msg when draw
    msgcontainer.classList.remove("hide"); // unhide the msg container
    disableBoxes();// disable the access to boxes
}

const disableBoxes = () =>{
    for(let box of boxes){ // running a loop to disable the access to boxes
        box.disabled = true; // disabling boxes
    }
}

const enableBoxes = () =>{
    for(let box of boxes){ // running a loop to enable the access to boxes
        box.disabled = false;// enabling boxes my making disable false
        box.innerText = "";// making the innertext of each box blank
    }
}

boxes.forEach((box)=>{// running loop for each box 
    box.addEventListener("click", ()=>{// adding of click event to each box
        if(turn0){// checking whether player0 turn or not
            box.innerText = "O";// putting O in the box clicked
            turn0 = false;// altering the turn for playerX
            box.style.color = "blue"; // making the color of every O as blue
        }
        else{
            box.innerText = "X";// putting X in the box clicked
            turn0 = true;// altering the turn for playerO
            box.style.color = "#b0413e"; // making the color of every X as brown
        }
        box.disabled = true; //USED TO DISABLE BUTTONS AFTER IT IS CLICKED ONCE.
        c++;// increment of counter when a button is clicked
        let isWinner = checkWinner();// checking and storing in bool whether ther is winner or not

        if(c === 9 && !isWinner){//condition to check draw i.e when all boxes are filled and no winner is there.
            drawGame();
        }
    });
});

const checkWinner = () =>{
    for(let pattern of winPatterns){// we check every array of array of arrays using a loop
        // store innerText at each index of every array of array of arrays
        let pos1Val = boxes[pattern[0]].innerText;
        let pos2Val = boxes[pattern[1]].innerText;
        let pos3Val = boxes[pattern[2]].innerText;

        //condition to check winner
        if(pos1Val != "" && pos2Val != "" && pos3Val != ""){//when none of the checking position is empty 
            if(pos1Val === pos2Val && pos2Val === pos3Val){// and value at each checking posn must be same i.e either all X or all O.
                showWinner(pos1Val);// pass the function with winner to print 
                return true;// returning whether there is a winner or not
            }
        }
    }
}

const showWinner = (winner)=> {
    msg.innerText = `CONGRATULATIONS..!! WINNER IS ${winner}`;// print msg when there is a winner
    msgcontainer.classList.remove("hide");
    resetbtn.classList.add("hide");// unhide the msg container
    disableBoxes();// disable the access to boxes
}

newbtn.addEventListener("click", resetGame); // adding of an event to the NEW BUTTON

resetbtn.addEventListener("click", resetGame);// adding of an event to the RESET BUTTON