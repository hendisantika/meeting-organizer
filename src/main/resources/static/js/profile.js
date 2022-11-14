(function ($) {

    $(document).ready(function () {
        var profileRoutes = ['/profile', '/profile/edit'];
        var profileRegexRoutes = [];

        addClassToNavigationTab(profileRoutes, profileRegexRoutes);
    });

})(jQuery);