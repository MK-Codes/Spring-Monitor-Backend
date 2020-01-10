document.getElementById("defaultOpen").click();

var currentURL = "";

function openTab(evt, tabName) {

    var i,
    tabcontent,
    tablinks;
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

function generateTabs() {

    fetch(/*[[${url}}]]*/ "", {
        cache: "no-store"
    })
    .then(response => response.text())
    .then((response) => {
        var OBJ = JSON.parse(response);
        // Sorting the object based on its status
        // Red will be displayed first, then amber, etc.
        var allCat = [];
        for (i = 0; i < OBJ.length; i++) {
            allCat.push(OBJ[i].category);
        }
        var allCatUnique = Array.from(new Set(allCat));
        console.log("showing unique tabs")
        console.log(allCatUnique);

        //                var toFilterBy = "<a href=\"#\" onclick=\"refreshData('all')\">all</a></br>";
        var toFilterBy = "<button class=\"tablinks\" onclick=\"refreshData('all')\" id=\"defaultOpen\">Monitor</button>";

        for (var i = 0; i < allCatUnique.length; i++) {
            toFilterBy = toFilterBy +
                "<button class=\"tablinks\" onclick=\"refreshData('" + allCatUnique[i] + "')\" id=\"" + allCatUnique[i] + "\">" + allCatUnique[i] + "</button>";
            //                    "<a href=\"#\" onclick=\"refreshData('" + allCatUnique[i]+ "')\">"+allCatUnique[i]+"</a> </br>"

        }
        console.log(toFilterBy);
        document.getElementById("tab").innerHTML = toFilterBy;
    })

    .catch(function (error) {
        console.error("Something died!");
    });
}

function refreshData(param) {
    console.log("Fetching data...");
    var url = "";
    if (param) {
        if (param == "all") {

            url = /*[[${url}]]*/ "https://mk-codes.co.uk/json";
        } else {
            // TODO
            url = /*[[${url}}]]*/ + param;
        }

        currentURL = url;
    } else if (currentURL !== "") {
        url = currentURL;
    } else if (!param || param === "") {

        url = /*[[${url}]]*/ "https://mk-codes.co.uk/json";
    }

    console.log("CURRENT URL" + currentURL);

    fetch(url, {
        cache: "no-store"
    })
    .then(response => response.text())
    .then((response) => {

        var OBJ = JSON.parse(response);
        console.log("Objects retrieved and parsed.");

        // Sorting the object based on its status
        // Red will be displayed first, then amber, etc.

        OBJ.sort(function (a, b) {
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
        for (var i = 0; i < OBJ.length; i++) {
            txt = txt +
                "<div class=\"grid-item\" id=\"" + OBJ[i].status + "\"" +
                "><div class=\"grid-item-inner\">" +
                "<p class=\"monitor-content\" style=\"text-align:center;\">" + OBJ[i].description + "</p>" +
                "</div>" +
                "</div>";
        }
        txt = txt + "</div>";
        document.getElementById("Monitor").innerHTML = txt;
        console.log("Objects displayed.");
    })
    .catch(function (error) {
        console.error("Something died!");
    });
}

generateTabs();
window.onload = window.setInterval(refreshData, 5_000);
refreshData(currentURL);
