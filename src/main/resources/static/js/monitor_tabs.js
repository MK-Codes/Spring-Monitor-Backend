var itemList;

function openTab(evt, tabName) {

    var tabcontent = document.getElementsByClassName("tabcontent");

    for (var i = 0; i < tabcontent.length; i++) {
//        tabcontent[i].style.display = "none";
    }

    var tablinks = document.getElementsByClassName("tablinks");

    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";

    tabFilter(tabName);
}

function refreshData() {

    fetch(dataURL, {
        cache: "no-store"
    })
    .then(response => response.text())
    .then((response) => {
        itemList = JSON.parse(response);

        // Sorting the object based on its status
        // Red will be displayed first, then amber, etc.
        itemList.sort(function (a, b) {
            const statusEnum = {
                RED: 0,
                AMBER: 1,
                GREEN: 2,
                GREY: 3,
                BLUE: 4
            }
            return statusEnum[a.status] - statusEnum[b.status];
        });
        console.log("Length: " + itemList.length);
        generateTabs(itemList);
    })
    .catch(function (error) {
        console.error("Something died: " + error);
    });
}

function generateTabs(items) {

    console.log("Generating tabs");
    var allCat = [];

    for (i = 0; i < items.length; i++) {
        allCat.push(items[i].category);
    }

    var allCatUnique = Array.from(new Set(allCat));
    var toFilterBy = "<button class=\"tablinks\" onclick=\"tabFilter('all')\" id=\"defaultOpen\" clicked>Monitor</button>";

    for (var i = 0; i < allCatUnique.length; i++) {
        toFilterBy = toFilterBy + "<button class=\"tablinks\" onclick=\"tabFilter('" +
            allCatUnique[i] + "')\" id=\"" + allCatUnique[i] + "\">" + allCatUnique[i] + "</button>";
    }
    document.getElementById("tab").innerHTML = toFilterBy;
    var isOptionChecked = false;
    for (var i = 0; i < document.getElementsByClassName('tablinks').length; i++) {
        if (document.getElementsByClassName('tablinks')[i].checked) {
            isOptionChecked = true;
        }
        if (!isOptionChecked) {
            document.getElementById('defaultOpen').checked = true;
        }
    }
    console.log("Tabs generated");
}

function tabFilter(tabName) {

    console.log("Sorting.\nItem list length: " + itemList.length + ".\nTab name: " + tabName);

    // iterate through objects
    var txt = "<div class=\"grid-container\">";
            for (var i = 0; i < itemList.length; i++) {
                if ((itemList[i].category == tabName) || (tabName == 'all')) {
                console.log("Item was found: " + itemList[i].name);
                    txt = txt +
                        "<div class=\"grid-item\" id=\"" + itemList[i].status + "\"" +
                        "><div class=\"grid-item-inner\">" +
                        "<p class=\"monitor-content\" style=\"text-align:center;\">" + itemList[i].description + "</p>" +
                        "</div>" +
                        "</div>";
                }
            }
            txt = txt + "</div>";
            document.getElementById("Monitor").innerHTML = txt;
    //
}

window.onload = window.setInterval(refreshData, 10_000);
refreshData();
//window.onload(document.getElementById("defaultOpen").click());
