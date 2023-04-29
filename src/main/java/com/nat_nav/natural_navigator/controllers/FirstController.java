package com.nat_nav.natural_navigator.controllers;


import com.nat_nav.natural_navigator.entity.Hotel;
import com.nat_nav.natural_navigator.repositories.HotelRepository;
import com.nat_nav.natural_navigator.repositories.PhotoRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.Math.sqrt;

@Controller
public class FirstController {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping("/findHotels")
    public String index(@RequestParam(value = "qty",required = false,defaultValue = "0") int qty,
                        @RequestParam(value = "days",required = false,defaultValue = "0") int days,
                        @RequestParam(value = "campaign",required = false) String campaign,
                        @RequestParam(value = "children",required = false,defaultValue = "0") int children ,

                        @RequestParam(value = "comfort",required = false,defaultValue = "0") int comfort ,
                        @RequestParam(value = "distance",required = false,defaultValue = "0") int distance ,
                        @RequestParam(value = "price",required = false,defaultValue = "0") int price ,
                        @RequestParam(value = "activity",required = false,defaultValue = "0") int activity ,
                        @RequestParam(value = "safety",required = false,defaultValue = "0") int safety ,

                        @RequestParam(value = "active_recreation_on_the_water",required = false,defaultValue = "0") int active_recreation_on_the_water,
                        @RequestParam(value = "fishing",required = false,defaultValue = "0") int fishing,
                        @RequestParam(value = "football",required = false,defaultValue = "0") int football,
                        @RequestParam(value = "volleyball",required = false,defaultValue = "0") int volleyball,
                        @RequestParam(value = "table_tennis",required = false,defaultValue = "0") int table_tennis,
                        @RequestParam(value = "tennis",required = false,defaultValue = "0") int tennis,
                        @RequestParam(value = "cycling",required = false,defaultValue = "0") int cycling,

                        @RequestParam(value = "distance_from_Kazan",required = false,defaultValue = "0") int distance_from_Kazan,
                        @RequestParam(value = "cost_of_stay_per_day",required = false,defaultValue = "0") int cost_of_stay_per_day,

                        Model model
                        ){

        int count = 1;
        model.addAttribute("count",count);
        model.addAttribute("qty",qty);
        model.addAttribute("days",days);
        model.addAttribute("campaign",campaign);
        model.addAttribute("children",children);
        model.addAttribute("comfort",comfort);
        model.addAttribute("distance",distance);
        model.addAttribute("price",price);
        model.addAttribute("activity",activity);
        model.addAttribute("safety",safety);
        model.addAttribute("active_recreation_on_the_water",active_recreation_on_the_water);
        model.addAttribute("fishing",fishing);
        model.addAttribute("football",football);
        model.addAttribute("volleyball",volleyball);
        model.addAttribute("table_tennis",table_tennis);
        model.addAttribute("tennis",tennis);
        model.addAttribute("cycling",cycling);
        model.addAttribute("distance_from_Kazan",distance_from_Kazan);
        model.addAttribute("cost_of_stay_per_day",cost_of_stay_per_day);


        //System.out.println("campaign:"+campaign);

        int family  = (campaign!=null&&campaign.equals("family")) ? 1:0;
        //System.out.println("family:"+family);
        int the_youth  = (campaign!=null&&campaign.equals("the_youth")) ? 1:0;
        int old_friends  = (campaign!=null&&campaign.equals("old_friends")) ? 1:0;

        /*active_recreation_on_the_water  = (active_recreation_on_the_water!=null) ? "1":"0";
        fishing  = (fishing!=null) ? "1":"0";
        football  = (football!=null) ? "1":"0";
        volleyball  = (volleyball!=null) ? "1":"0";
        table_tennis  = (table_tennis!=null) ? "1":"0";
        tennis  = (tennis!=null) ? "1":"0";
        cycling  = (cycling!=null) ? "1":"0";*/
        /*if (children == null) children=0;
        if (active_recreation_on_the_water == null) active_recreation_on_the_water=0;
        if (fishing == null)fishing =0;
        if (football == null)football =0;
        if ( == null) =0;
        if ( == null) =0;
        if ( == null) =0;
        if ( == null) =0;
        if ( == null) =0;
        if ( == null) =0;
        volleyball
                table_tennis
        tennis
                cycling
        distance_from_Kazan
                cost_of_stay_per_day*/

        /*System.out.println("family:"+family);
        System.out.println(":"+children);
        System.out.println(the_youth);
        System.out.println(old_friends);
        System.out.println(comfort);
        System.out.println(distance);
        System.out.println(price);
        System.out.println(activity);
        System.out.println(safety);
        System.out.println(active_recreation_on_the_water);
        System.out.println(fishing);
        System.out.println(football);
        System.out.println(volleyball);
        System.out.println(table_tennis);
        System.out.println(tennis);
        System.out.println(cycling);
        System.out.println(distance_from_Kazan);
        System.out.println(cost_of_stay_per_day);*/

        List<Hotel>BestHotels=hotelRepository.findBestHotels(
                family,
                children,
                the_youth,
                old_friends,
                comfort,
                distance,
                price,
                activity,
                safety,
                active_recreation_on_the_water,
                fishing,
                football,
                volleyball,
                table_tennis,
                tennis,
                cycling,
                distance_from_Kazan,
                cost_of_stay_per_day,1,1,1,1,1,qty,days);
        for(Hotel hotel: BestHotels){
            hotel.setTotal(hotel.getFamily()*family +
                    hotel.getChildren()*sqrt(children)+
                    hotel.getTheYouth()*the_youth+
                    hotel.getOldFriends()*old_friends+

                    hotel.getComfort()*comfort+
                    hotel.getDistance()*distance+
                    hotel.getPrice()*price+
                    hotel.getActivity()*activity+
                    hotel.getSafety()*safety+

                    hotel.getActiveRecreationOnTheWater()*active_recreation_on_the_water+
                    hotel.getFishing()*fishing+
                    hotel.getFootball()*football+
                    hotel.getVolleyball()*volleyball+
                    hotel.getTableTennis()*table_tennis+
                    hotel.getTennis()*tennis+
                    hotel.getCycling()*cycling);
        }

        if(comfort==0){
            model.addAttribute("hotels",hotelRepository.SortById());
        } else
        model.addAttribute("hotels",BestHotels/*hotelRepository.findBestHotels(
                family,
                children,
                the_youth,
                old_friends,
                comfort,
                distance,
                price,
                activity,
                safety,
                active_recreation_on_the_water,
                fishing,
                football,
                volleyball,
                table_tennis,
                tennis,
                cycling,
                distance_from_Kazan,
                cost_of_stay_per_day,1,1,1,1,1,qty,days)*/);

        model.addAttribute("topHotels",hotelRepository.findBestHotels(
                family,
                children,
                the_youth,
                old_friends,
                comfort,
                distance,
                price,
                activity,
                safety,
                active_recreation_on_the_water,
                fishing,
                football,
                volleyball,
                table_tennis,
                tennis,
                cycling,
                distance_from_Kazan,
                cost_of_stay_per_day,1,1,1,1,1,qty,days).subList(0,3));
        //model.addAttribute("hotels", hotelRepository.findBestHotels(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1));
        //model.addAttribute("hotels",hotelRepository.findAll());

        System.out.println(hotelRepository.getTotal(
                family,
                children,
                the_youth,
                old_friends,
                comfort,
                distance,
                price,
                activity,
                safety,
                active_recreation_on_the_water,
                fishing,
                football,
                volleyball,
                table_tennis,
                tennis,
                cycling,
                distance_from_Kazan,
                cost_of_stay_per_day,1,1,1,1,1,qty,days,1));

        HashMap<Double,Hotel> HotelTotal=new HashMap<>() ;
        for(Hotel hotel:BestHotels){
            System.out.println(hotel.getId());
            HotelTotal.put(hotelRepository.getTotal(family,
                    children,
                    the_youth,
                    old_friends,
                    comfort,
                    distance,
                    price,
                    activity,
                    safety,
                    active_recreation_on_the_water,
                    fishing,
                    football,
                    volleyball,
                    table_tennis,
                    tennis,
                    cycling,
                    distance_from_Kazan,
                    cost_of_stay_per_day,1,1,1,1,1,qty,days,hotel.getId()),hotel);
        }

        Map<Double, Hotel> SortedBestHotels = new TreeMap<>(HotelTotal).descendingMap();

        for(Map.Entry<Double,Hotel> pair: SortedBestHotels.entrySet()){

            System.out.println("Печать Хэшмапа\n");
            System.out.println(pair.getKey());
            System.out.println(pair.getValue().getName());

        }
        System.out.println("СПИСООООООК:::::");
        System.out.println(SortedBestHotels.values());
        System.out.println("----------------------------------------------------------");
        System.out.println(BestHotels);
        model.addAttribute("hotelTotal",SortedBestHotels);

        HashMap<Integer, Integer> test=new HashMap<>();
        test.put(1,1);
        test.put(2,2);
        model.addAttribute("test",test);
        return "index";
    }


    @GetMapping("/hotel")
    public String hotel(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("hotel",hotelRepository.getReferenceById(id));

        return "hotel";
    }

    @GetMapping("/aboutUs")
    public String aboutUs(){

        hotelRepository.getReferenceById(1).getPhotoList().size();
        return "aboutUs";
    }


}
