package com.nat_nav.natural_navigator.controllers;


import com.nat_nav.natural_navigator.entity.Hotel;
import com.nat_nav.natural_navigator.entity.ResidenceHistory;
import com.nat_nav.natural_navigator.entity.User;
import com.nat_nav.natural_navigator.repositories.*;
import com.nat_nav.natural_navigator.services.*;
import com.nat_nav.natural_navigator.util.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ResidenceHistoryRepository residenceHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserService userService;

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

                        Model model,Authentication authentication
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

        /*List<Hotel>BestHotels=hotelService.getBestHotels(family,
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

        HashMap<Double,Hotel> HotelTotal=new HashMap<>() ;
        for(Hotel hotel:BestHotels){
            HotelTotal.put(hotelService.getTotal(family,
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
        }*/

        /*Map<Double, Hotel> SortedBestHotels = hotelService.getSortedBestHotels(family,
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
                cost_of_stay_per_day,1,1,1,1,1,qty,days);*/







        if(comfort==0){
            List<Hotel> recommendedHotels = null;
            if(authentication!=null){
                Optional<User> user1 = userService.findByEmail(authentication.getName());
                recommendedHotels = recommendationService.getRecommendedHotels(user1.get().getId());
            } else{
                recommendedHotels = recommendationService.getRecommendedHotels(4);
            }

            Map<Double,Hotel> HotelsMapNoTotalRecommended=new TreeMap<>() ;
            double id_count=1;
            for(Hotel hotel:recommendedHotels){ //здесь
                System.out.println(hotel.getName());
                HotelsMapNoTotalRecommended.put(id_count,hotel);
                id_count++;
            }
            model.addAttribute("hotelTotal",HotelsMapNoTotalRecommended);

            int totalCounter=1;
            model.addAttribute("totalCounter",totalCounter);
        } else{
            Map<Double,Hotel> Top3=hotelService.Top3(family,
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

            model.addAttribute("topHotels",Top3);
            Map<Double,Hotel> TopDown3=hotelService.TopDown3(family,
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
            model.addAttribute("hotelTotal",TopDown3);

            int totalCounter=4;
            model.addAttribute("totalCounter",totalCounter);
        }



        boolean auth = SecurityContextHolder.getContext().getAuthentication().getName()=="anonymousUser";
        model.addAttribute("auth",!auth);

        /*if(authentication!=null){
            Optional<User> user = userRepository.findByEmail(authentication.getName());
            //System.out.println("userid"+(user.get().getId()));
            model.addAttribute("recommededHotels",recommendedHotels);
        } else {
            model.addAttribute("recommededHotels",recommendationService.getRecommendedHotels(4));
        }*/

        /*System.out.println("rec:");
        for(Hotel i:recommendedHotels){
            System.out.println(i.getName());
        }*/

        /*if(comfort==0){
            int totalCounter=1;
            model.addAttribute("totalCounter",totalCounter);
        } else{
            int totalCounter=4;
            model.addAttribute("totalCounter",totalCounter);
        }*/

        return "index";
    }


    @GetMapping("/hotel")
    public String hotel(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("hotel",hotelRepository.getReferenceById(id));
        boolean auth = SecurityContextHolder.getContext().getAuthentication().getName()=="anonymousUser";
        model.addAttribute("auth",!auth);

        model.addAttribute("residenceHistory",hotelRepository.getReferenceById(id).getResidenceHistoryList());


        ResidenceHistory new_residenceHistory= new ResidenceHistory();
        model.addAttribute("new_residenceHistory",new_residenceHistory);

        System.out.println("----------------------------");
        System.out.println("Matrix of recommendations:");
        for(Object[] obj : residenceHistoryRepository.findRatings()){
            System.out.print(obj[0]);
            System.out.print(obj[1]);
            System.out.println(obj[2]);
        }
        System.out.println("--------------------------------");

        recommendationService.getRecommendedHotels(1);

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
    public FirstController(UserValidator userValidator, RegistrationService registrationService, NewResidenceHistoryService newResidenceHistoryService) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
        this.newResidenceHistoryService = newResidenceHistoryService;
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

    private final NewResidenceHistoryService newResidenceHistoryService;

    @PostMapping("/hotel")
    public String addReview(@RequestParam(value = "id") int hotelId,
                            @ModelAttribute("new_residenceHistory") ResidenceHistory new_residenceHistory, Authentication authentication/*,Model model*/) {

        Optional<User> user = userRepository.findByEmail(authentication.getName());


        new_residenceHistory.setHotel_rev(hotelRepository.getReferenceById(hotelId));
        new_residenceHistory.setUsers_rev(user.get());


        residenceHistoryRepository.insert(new_residenceHistory.getCheckInDate(),new_residenceHistory.getCheckOutDate(),
                new_residenceHistory.getTotalCost(),new_residenceHistory.getReview(),new_residenceHistory.getGrade(),user.get().getId(),hotelId);


        return "redirect:/hotel?id="+hotelId;
    }




}
