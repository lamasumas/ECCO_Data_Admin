
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
        <b>Answer `+nextElement +`:</b> <input id="answer"`+ nextElement + ` type="text"> <b>Energy type:</b> 
        <select id="energyType`+nextElement+`">
            <option value="Wind">Wind</option>
            <option value="PV">PV</option>
            <option value="Hydro">Hydro</option>
            <option value="Manure">Manure</option>
            <option value="Chips">Chips</option>
            <option value="Sawdust">Sawdust</option>
        </select> 
         <b>Points:</b> <input id="points`+nextElement+`" type="number"><hr>
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