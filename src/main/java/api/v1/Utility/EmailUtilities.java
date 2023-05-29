package api.v1.Utility;

public class EmailUtilities {

    public static String generateSendBody() {
        return
            "<table cellpadding=\"0\" cellspacing=\"0\" style=\"vertical-align: -webkit-baseline-middle; font-size: medium; font-family: Arial;\">" +
            "<tbody>" +
            "<span>Your auth code expires in <b>3 min</b></span>" +
            "<tr>" +
            "<td width=\"190\">" +
            "<img src=\"https://app.logo.com/app/api/v2/images?logo=logo_119e65c0-fd71-40fb-8d96-22216b890a8c&amp;u=2023-04-25T09%3A28%3A22.439Z&amp;format=png&amp;fit=contain&amp;quality=100&amp;margins=100&amp;height=285&amp;width=380\" role=\"presentation\" width=\"190\" style=\"max-width: 190px; display: inline; text-align: left; border-radius: 5px;\">" +
            "</td>" +
            "<td>" +
            "<table cellpadding=\"20\" cellspacing=\"0\" style=\"vertical-align: -webkit-baseline-middle; font-size: medium; font-family: Arial; width: 100%;\">" +
            "<tbody>" +
            "<tr>" +
            "<td>" +
            "<p style=\"margin: 0px; font-size: 15px; font-weight:bold; color: #111; line-height: 20px;\">" +
            "<span>MAIL Service</span>" +
            "</p>" +
            "<p style=\"margin: 0px; color: #687087; font-size: 14px; line-height: 20px;\">" +
            "<span>domain metalloy.tech</span>" +
            "</p>" +
            "<p style=\"margin: 0px; color: #687087; font-size: 14px; line-height: 20px;\">" +
            "</p>" +
            "</td>" +
            "</tr>" +
            "</tbody>" +
            "</table>" +
            "</td>" +
            "</tr>" +
            "</tbody>" +
            "</table>";
    }

}
