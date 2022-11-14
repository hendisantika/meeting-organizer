(function ($) {

    $(document).ready(function () {

        var locationRoutes = ['/location', '/location/browse', '/location/add'];
        var locationsRegexRoutes = [/\/location\/\d\/details/];

        $('#locationTable').DataTable();

        addClassToNavigationTab(locationRoutes, locationsRegexRoutes);
    });

})(jQuery);