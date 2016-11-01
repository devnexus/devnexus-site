<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>
<% pageContext.setAttribute("scheduleItemTypeAdminsitrative", ScheduleItemType.ADMINISTRATIVE); %>
<% pageContext.setAttribute("scheduleItemTypeEveningReception", ScheduleItemType.EVENING_RECEPTION); %>
<% pageContext.setAttribute("scheduleItemTypeBreak", ScheduleItemType.BREAK); %>
<% pageContext.setAttribute("scheduleItemTypeKeynote", ScheduleItemType.KEYNOTE); %>
<% pageContext.setAttribute("scheduleItemTypeRegistration", ScheduleItemType.REGISTRATION); %>
<% pageContext.setAttribute("scheduleItemTypeMorningReception", ScheduleItemType.MORNING_RECEPTION); %>
<% pageContext.setAttribute("scheduleItemTypeSession", ScheduleItemType.SESSION); %>

<title>${contextEvent.title} | Schedule</title>


<section class="container-fluid schedule-config" >
    <h1 class="featured-header">
        SCHEDULE COMING SOON
    </h1>

</section>

<section class="container-fluid schedule" >
    <div class="conference-information">
        <p style="text-align: center">We are preparing our schedule for 2017.  Please join our mailing list to be informed of updates.</p>
    </div>    
    <div class="col-md-8 col-md-offset-2">

        <div class="col-md-8 col-md-offset-2"style="margin-bottom: 1em;">

            <!-- Begin MailChimp Signup Form -->

            <style type="text/css">
                #mc_embed_signup{background:#fff; clear:left; font:14px Helvetica,Arial,sans-serif; }
                /* Add your own MailChimp form style overrides in your site stylesheet or in this style block.
                   We recommend moving this block and the preceding CSS link to the HEAD of your HTML file. */
            </style>
            <div id="mc_embed_signup">
                <form action="//ajug.us7.list-manage.com/subscribe/post?u=0b3b17489713c9e7c62595dd3&amp;id=a1fba8f26f" method="post" id="mc-embedded-subscribe-form" name="mc-embedded-subscribe-form" class="validate" target="_blank" novalidate>
                    <div id="mc_embed_signup_scroll" class="conference-information ">

                        <div class="mc-field-group">

                            <input style="height:80px;font-size: 20px;border-radius: 0;font-weight: normal;width:100%" type="email" placeholder="E-Mail Address" value="" name="EMAIL" class="required email input-control" id="mce-EMAIL">
                        </div>
                        <div id="mce-responses" class="clear">
                            <div class="response" id="mce-error-response" style="display:none"></div>
                            <div class="response" id="mce-success-response" style="display:none"></div>
                        </div>    <!-- real people should not fill this in and expect good things - do not remove this or risk form bot signups-->
                        <div style="position: absolute; left: -5000px;" aria-hidden="true"><input type="text" name="b_0b3b17489713c9e7c62595dd3_a1fba8f26f" tabindex="-1" value=""></div>
                        <input style="margin-top: 50px; color: white;background-color: black;border-radius: 0"type="submit" value="Subscribe" name="subscribe" id="mc-embedded-subscribe" class=" btn btn-primary btn-block">

                    </div>
                </form>
            </div>
            <script type='text/javascript' src='//s3.amazonaws.com/downloads.mailchimp.com/js/mc-validate.js'></script><script type='text/javascript'>(function ($) {
                    window.fnames = new Array();
                    window.ftypes = new Array();
                    fnames[0] = 'EMAIL';
                    ftypes[0] = 'email';
                    fnames[1] = 'FNAME';
                    ftypes[1] = 'text';
                    fnames[2] = 'LNAME';
                    ftypes[2] = 'text';
                }(jQuery));
                var $mcj = jQuery.noConflict(true);</script>
            <!--End mc_embed_signup-->
        </div>
    </div>

</section>
