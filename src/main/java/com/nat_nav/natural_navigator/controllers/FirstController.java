package com.nat_nav.natural_navigator.controllers;


import com.nat_nav.natural_navigator.entity.Hotel;
import com.nat_nav.natural_navigator.entity.User;
import com.nat_nav.natural_navigator.repositories.HotelRepository;
import com.nat_nav.natural_navigator.repositories.PhotoRepository;
import com.nat_nav.natural_navigator.services.RegistrationService;
import com.nat_nav.natural_navigator.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

        int family  = (campaign!=null&&campaign.equals("family")) ? 1:0;
        int the_youth  = (campaign!=null&&campaign.equals("the_youth")) ? 1:0;
        int old_friends  = (campaign!=null&&campaign.equals("old_friends")) ? 1:0;





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
        /*for(Hotel hotel: BestHotels){
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
        }*/

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
        model.addAttribute("hotelTotal",SortedBestHotels);

        if(comfort==0){
            model.addAttribute("hotels",hotelRepository.SortById());
        } else{
        model.addAttribute("hotels",BestHotels);
        }

        System.out.println("TOOOOOOOOOP3");
        Map<Double,Hotel> TopF3=new TreeMap<>();
        Iterator<Map.Entry<Double,Hotel>> iterator=SortedBestHotels.entrySet().iterator();
        for(int i=0;i<3&& iterator.hasNext();i++){
            Map.Entry<Double,Hotel> pair = iterator.next();
            TopF3.put(pair.getKey(),pair.getValue());
            System.out.println(pair.getValue().getName());
        }
        Map<Double,Hotel> Top3=new TreeMap<>(TopF3).descendingMap();

        model.addAttribute("topHotels",Top3);
        //model.addAttribute("hotels", hotelRepository.findBestHotels(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1));
        //model.addAttribute("hotels",hotelRepository.findAll());

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

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    private final UserValidator userValidator;
    public FirstController(UserValidator userValidator, RegistrationService registrationService) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
    }
    private final RegistrationService registrationService;
    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user){
        return "registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user")@Valid User user, BindingResult bindingResult){
        userValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors()){
            return "/registration";
        }
        registrationService.register(user);
        return "redirect:/login";
    }






}
