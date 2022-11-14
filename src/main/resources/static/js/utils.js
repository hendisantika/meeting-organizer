function addClassToNavigationTab(paths, regexPaths) {
    var navigationTabs = $("#navigation-tabs li a");

    navigationTabs.each(function () {
        var currentTab = $(this)[0];

        if (isTabActive(currentTab.pathname, paths, regexPaths)) {
            $(currentTab).parent().addClass('active');
        }
    });

}

function isTabActive(url, paths, regexPaths) {
    return isCurrentUrlInPaths(paths, url) || urlMatchesAnyRegexPath(regexPaths, url);
}

function isCurrentUrlInPaths(paths, url) {
    return paths.indexOf(url) !== -1;
}

function urlMatchesAnyRegexPath(regexPaths, url) {
    var matches = false;
    var i = 0;

    for (i; i < regexPaths.length; i++) {
        var currentRegex = regexPaths[i];

        if (url.match(currentRegex)) {
            matches = true;
            break;
        }
    }

    return matches;
}
