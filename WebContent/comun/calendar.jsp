<%-- 
    Author     : Alejandro
    Description: Incluye scripts necesarios para mostrar calendarios.
--%>

<s:url id="urlCalendar1" value="/scripts/calendar/calendar.js" />
<s:url id="urlCalendar2" value="/scripts/calendar/lang/calendar-es.js" />
<s:url id="urlCalendar3" value="/scripts/calendar/calendar-setup.js" />

<script src="${urlCalendar1}"></script>
<script src="${urlCalendar2}"></script>
<script src="${urlCalendar3}"></script>

<script type="text/javascript">
    
    function calendar(idInput, idButton, time) {
        Calendar.setup({
            inputField     :    idInput,    // id of the input field
            ifFormat       :    "%Y-%m-%d %H:%M", // format of the input field
            button         :    idButton,   // trigger for the calendar (button ID)
            step           :    1,          // show all years in drop-down boxes (instead of every other year as default)
            showsTime      :    time
        });
    }
    
</script>
