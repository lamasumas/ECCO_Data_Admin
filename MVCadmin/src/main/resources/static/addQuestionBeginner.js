
function setVisibleAnswerInputs(){
    var selectorValue = document.getElementById("numberAnswer").value;
    var selectedValueInteger = parseInt(selectorValue);
    var answerSpan = document.getElementById("answers");

    if( selectedValueInteger > answerSpan.childElementCount)
    {
        var nextElement= (answerSpan.childElementCount+1);
        var generalSpan = document.createElement("span");
        generalSpan.id = ("answer" +nextElement );
        var element = `
        <b>Answer `+nextElement +`:</b> <input  name="answer`+ nextElement + `" type="text"> <b>Energy type:</b> 
        <select  name="energyType`+nextElement+`">
            <option value="Wind">Wind</option>
            <option value="PV">PV</option>
            <option value="Hydro">Hydro</option>
            <option value="Manure">Manure</option>
            <option value="Chips">Chips</option>
            <option value="Sawdust">Sawdust</option>
        </select> 
         <b>Points:</b> <input  name="points`+nextElement+`" type="number" value="0"><hr>
         `;
         generalSpan.innerHTML = element;

         answerSpan.appendChild(generalSpan);
         setVisibleAnswerInputs();
         

    }else if( selectedValueInteger < answerSpan.childElementCount)
    {
        answerSpan.removeChild(answerSpan.lastChild)
        setVisibleAnswerInputs();
    }

}


