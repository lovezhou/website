
if (jQuery) (
function (jQuery) {
    jQuery.extend(jQuery.fn, {
        tablePaging: function (options) {
            jQuery(this).each(function () {
                settings = jQuery.extend({
                    id: jQuery(this).attr('id'), // The ID of the object being paging
                    pageSize: 10, // The size of one page
                    sorting: false,
                    sortDirection: 'asc', // asc & desc
                    sortSelector: '',
                    sortType: '', //number,string,date
                    onPaged: null // // Function to run after paged
                }, options);
            });
            $(this).each(function () {
                if (settings.sorting) {
                    jQuery(this).tableSort(settings.sortSelector, settings.sortType, settings.sortDirection);

                }
                var pageSize = parseInt(settings.pageSize);

                var trResult = jQuery('#' + jQuery(this).attr('id')).find('tbody:first tr');
                var trLen = trResult.length;
                var pageIndex = 0;
                if (!jQuery(this).data("pageindex")) {
                    jQuery(this).data("pageindex", "0");
                };
                pageIndex = parseInt(jQuery(this).data("pageindex"));
                if (pageIndex * pageSize == trLen && trLen != 0) {
                    pageIndex = pageIndex - 1; // make sure the last page will show right
                    jQuery(this).data("pageindex", pageIndex);
                }
                jQuery('#' + jQuery(this).attr('id')).find('tbody').each(function (i, element) {
                    var trs = $(this).find('tr');
                    var trsLen = trs.length;
                    trs.hide();
                    trs.slice(pageIndex * pageSize, (pageIndex + 1) * pageSize).show();
                });

                var allPage = Math.ceil(parseInt(trLen) / pageSize);
                if (allPage > 1) {
                    jQuery("#" + jQuery(this).attr('id') + "Paging").remove();
                    jQuery("#" + jQuery(this).attr('id')).after('<div id="' + jQuery(this).attr('id') + 'Paging" class="mod_page noprint"></div>');
                    queue = '#' + jQuery(this).attr('id') + 'Paging';
                    jQuery(queue).append( 
						'<span class="page_total">共<span>'+allPage+'</span>条记录</span> '+
						'<span class="page_cur">当前<span> ' + (parseInt(jQuery(this).data("pageindex")) + 1) + '/' + allPage + ' </span>页</span> '+
						'<span class="page_list">'+
						'<a id="firstPage" tableid="' + jQuery(this).attr('id') + '" pageindex=0 href="javascript:;">首页</a> ' +
						'<a id="prePage"   tableid="' + jQuery(this).attr('id') + '" pageindex=' + (parseInt(jQuery(this).data("pageindex")) - 1) + ' href="javascript:;">上一页</a> '+
						'<a id="nextPage"  tableid="' + jQuery(this).attr('id') + '" pageindex=' + (parseInt(jQuery(this).data("pageindex")) + 1) + ' href="javascript:;">下一页</a> '+
						'<a id="lastPage"  tableid="' + jQuery(this).attr('id') + '" pageindex=' +(allPage-1) + ' href="javascript:;">末页</a> '+
						'</span>');
                    //首页点击
                    jQuery("#" + jQuery(this).attr('id') + "Paging").find("#firstPage").bind("click", settings, function (e) {
                        //var toPageIndex = settings.pageIndex - 1;
                        var toPageIndex = parseInt(jQuery(this).attr("pageindex"));
                        if (toPageIndex < 0) toPageIndex = allPage - 1;
                        jQuery("#" + jQuery(this).attr("tableid")).data("pageindex", toPageIndex);
                        jQuery("#" + jQuery(this).attr("tableid")).tablePaging(e.data);
                        e.preventDefault();
                    });
                    //上一页点击
                    jQuery("#" + jQuery(this).attr('id') + "Paging").find("#prePage").bind("click", settings, function (e) {
                        //var toPageIndex = settings.pageIndex - 1;
                        var toPageIndex = parseInt(jQuery(this).attr("pageindex"));
                        if (toPageIndex >= 0){
	                        jQuery("#" + jQuery(this).attr("tableid")).data("pageindex", toPageIndex);
	                        jQuery("#" + jQuery(this).attr("tableid")).tablePaging(e.data);
                        }
                        e.preventDefault();
                    });
                    //下一页点击
                    jQuery("#" + jQuery(this).attr('id') + "Paging").find("#nextPage").bind("click", settings, function (e) {
                        //var toPageIndex = settings.pageIndex + 1;
                        var toPageIndex = parseInt(jQuery(this).attr("pageindex"));
                        if (toPageIndex < allPage) {
	                        jQuery("#" + jQuery(this).attr("tableid")).data("pageindex", toPageIndex);
	                        jQuery("#" + jQuery(this).attr("tableid")).tablePaging(e.data);
                        }
                        e.preventDefault();

                    });
                    //末页点击
                    jQuery("#" + jQuery(this).attr('id') + "Paging").find("#lastPage").bind("click", settings, function (e) {
                        //var toPageIndex = settings.pageIndex + 1;
                        var toPageIndex = parseInt(jQuery(this).attr("pageindex"));
                        jQuery("#" + jQuery(this).attr("tableid")).data("pageindex", toPageIndex);
                        jQuery("#" + jQuery(this).attr("tableid")).tablePaging(e.data);
                        e.preventDefault();
                    });
                }
                else {
                    jQuery("#" + jQuery(this).attr('id') + "Paging").remove();
                }

                if (jQuery.isFunction(settings.onPaged)) {
                    settings.onPaged();
                }
            });
        },

        tableSort: function (sortBySelector, type, sortDirection) {
            jQuery('#' + jQuery(this).attr('id')).find('tbody').each(function (i, element) {
                var trs = jQuery(element).find('tr');
                var tmp = [];
                for (var i = 0; i < trs.length; i++) {
                    tmp.push(trs[i]);
                }
                if (type == "number") {
                    tmp.sort(function (a, b) {
                        var inta = Number($.trim($(a).find(sortBySelector).text()));
                        var intb = Number($.trim($(b).find(sortBySelector).text()));
                        var returnValue = 0;
                        if (inta > intb) returnValue = 1;
                        else if (inta < intb) returnValue = -1;
                        if (sortDirection == "desc") returnValue = -returnValue;
                        return returnValue;
                    });
                }
                else if (type === "date") {
                    tmp.sort(function (a, b) {
                        var datea = parseDate($.trim($(a).find(sortBySelector).text()));
                        var dateb = parseDate($.trim($(b).find(sortBySelector).text()));
                        var returnValue = 0;
                        if (datea > dateb) returnValue = 1;
                        else if (datea < dateb) returnValue = -1;
                        if (sortDirection == "desc") returnValue = -returnValue;
                        return returnValue;
                    });
                }
                else {
                    tmp.sort(function (a, b) {
                        var stra = $.trim($(a).find(sortBySelector).text());
                        var strb = $.trim($(b).find(sortBySelector).text());
                        var returnValue = 0;
                        if (stra > strb) returnValue = 1;
                        else if (stra < strb) returnValue = -1;
                        if (sortDirection == "desc") returnValue = -returnValue;

                        return returnValue;
                    });
                }

                for (var i = 0; i < tmp.length; i++) {
                    trs[i] = tmp[i];
                }

                jQuery(element).empty().append(trs);
            });

        }

    })
})(jQuery);

function parseDate(date) {
    return Date.parse(convertDateFormat(date)); // from en-GB format to en-US format,must greater than 01/01/1970;
}

function convertDateFormat(value) {
    return value = value.toString().replace(/\-/g, '/').replace(/\./g, '/').replace(/\s/g, '/');
}
