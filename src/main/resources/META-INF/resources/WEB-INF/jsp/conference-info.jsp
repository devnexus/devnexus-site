<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Conference Information </title>
<section class="container-fluid conference-information" >


    <h1 class="featured-header">
        CONFERENCE INFORMATION
    </h1>
    <div class="row ">
        <h2 class="center-block travel-address"><a href="https://www.google.com/maps/place/Georgia+World+Congress+Center/@33.76042,-84.3980223,17z/data=!4m2!3m1!1s0x0000000000000000:0x0072f65a339b8777">Georgia World Congress Center | 285 Andrew Young International Blvd NW, Atlanta, GA 30303
                | 404-223-4000</a></h2>
        <p>
            The Georgia World Congress Center website includes directions to the conference center from several directions as well as the airport.

            MARTA, Atlanta’s public transportation system, may be used to reach the conference, and the nearest train stop adjacent to the GWCC.
        </p>
    </div>
    <section class="col-lg-6 col-md-12 information-card">
        <h1>
            CONFERENCE LOCATION
        </h1>
        <img src="${ctx}/assets/img/venue.jpg" alt="CONFERENCE LOCATION">
        <h2>
            GEORGIA WORLD CONGRESS CENTER
        </h2>
        <p>
            The Georgia World Congress Center (GWCC) is located in downtown Atlanta at 285 Andrew Young Boulevard NW, adjacent to Centennial Olympic Park, the Georgia Dome, CNN Center and Phillips Arena. The Georgia World Congress Center is the third-largest convention center in the United States.
        </p>
    </section>

    <section class="col-lg-6 col-md-12 information-card">
        <h1>
            PARKING AND TRANSPORTATION
        </h1>
        <img src="${ctx}/assets/img/parking.jpg" alt="PARKING AND TRANSPORTATION">
        <h2>
            TRANSPORTATION TO THE CONFERENCE
        </h2>
        <p>
            The Georgia World Congress Center Authority operates three surface lots and two parking decks on campus managed by AAA Parking. There are over 5,600 parking spaces to make visiting the GWCCA convenient for guests. Lots are gated with an attendant on duty during all show/event hours. GWCCA Public Safety patrols the lots and decks. Emergency vehicle assistance is available for motorists in distress.<br><br>
            Public transportation is serviced by the Dome/GWCC/Phillips Arena/CNN Center MARTA station.<br><br>
            Uber and Lift are also recommended forms of transportation.<br><br>
        </p>
    </section>

    <section class="col-lg-6 col-md-12 information-card">
        <h1>
            HOTELS
        </h1>
        <img src="${ctx}/assets/img/omni-hotel-at-cnn-center-01.jpg" alt="omni hotel at cnn center">
        <h2>
            OMNI HOTEL AT CNN CENTER
        </h2>
        <p>
            This upscale high-rise hotel in the CNN Center is a 7-minute walk from the Georgia Aquarium. <br>
            <br>
            Elegant rooms with city views offer Wi-Fi (fee), flat-screen TVs and desks, plus coffeemakers and 24-hour room service. Some suites add fridges and sitting areas, while others feature separate living/dining areas and kitchenettes. A Coca Cola-themed suite is also available.


        </p>
        <a class="btn" href="#">BOOK NOW</a>
    </section>


    <section class="col-lg-6 col-md-12 information-card">
        <h1>
            &nbsp;
        </h1>
        <img src="${ctx}/assets/img/embassy.jpg" alt="EMBASSY SUITES CENTENNIAL OLYMPIC">
        <h2>
            EMBASSY SUITES CENTENNIAL OLYMPIC
        </h2>
        <p>
            Next to Centennial Olympic Park, this all-suite hotel with a glass-roofed atrium is a 9-minute walk from the Peachtree metro stop. <br>
            <br>
            Classic suites include microwaves, minifridges and coffeemakers, plus WiFi (fee), flat-screen TVs and separate living areas with pull-out sofas. Upgraded suites offer dining tables; some add terraces with views.<br>
        </p>
        <a class="btn" href="#">BOOK NOW</a>
    </section>
</section>
