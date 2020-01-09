document.getElementById("defaultOpen").click();

function openTab(evt, tabName) {

  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");

  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  tablinks = document.getElementsByClassName("tablinks");

  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}

function refreshData() {
  fetch("https://mk-codes.co.uk/json", {cache: "no-store"})
  .then(response => response.text())
        .then((response) => {

            var OBJ = JSON.parse(response);
            console.log("Objects retrieved and parsed.");

            // Sorting the object based on its status
            // Red will be displayed first, then amber, etc.

            OBJ.sort(function(a, b){
                const statusEnum = {
                    RED: 0,
                    AMBER: 1,
                    GREEN: 2,
                    GREY: 3,
                    BLUE: 4
                }
                return statusEnum[a.status] - statusEnum[b.status];
            });
            console.log("Objects sorted.");
            var txt = "<div class=\"grid-container\">";
            for(var i = 0; i < OBJ.length; i++){
                txt = txt +
                "<div class=\"grid-item\" id=\"" + OBJ[i].status + "\""+
                    "><div class=\"grid-item-inner\">" +
                        "<p class=\"monitor-content\" style=\"text-align:center;\">" + OBJ[i].description + "</p>" +
                     "</div>" +
                "</div>";
            }
            txt = txt + "</div>";
            document.getElementById("Monitor").innerHTML = txt;
            console.log("Objects displayed.");
        })
  .catch(function(error) {
  console.error("Something died!");
  });

}