package com.nat_nav.natural_navigator.controllers;


import com.nat_nav.natural_navigator.entity.Hotel;
import com.nat_nav.natural_navigator.entity.ResidenceHistory;
import com.nat_nav.natural_navigator.entity.User;
import com.nat_nav.natural_navigator.repositories.*;
import com.nat_nav.natural_navigator.services.RecommendationService;
import com.nat_nav.natural_navigator.services.RegistrationService;
import com.nat_nav.natural_navigator.services.NewResidenceHistoryService;
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

    /*@Autowired
    private AggregatedDataRepository aggregatedDataRepository;*/



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
            //System.out.println(hotel.getId());
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

        Map<Double,Hotel> HotelsMapNoTotal=new TreeMap<>() ;
        List<Hotel> recommendedHotels = null;
        if(authentication!=null){
            Optional<User> user1 = userRepository.findByEmail(authentication.getName());
            recommendedHotels = recommendationService.getRecommendedHotels(user1.get().getId());
        } else{
            recommendedHotels = recommendationService.getRecommendedHotels(1);
        }


        //System.out.println(hotelRepository.SortById().size());
        double id_count=1;
        for(Hotel hotel:recommendedHotels){ //здесь
            System.out.println(hotel.getName());
            HotelsMapNoTotal.put(id_count,hotel);
            id_count++;
        }
        //System.out.println(HotelsMapNoTotal);


        System.out.println("recHash:");
        for(Map.Entry i:HotelsMapNoTotal.entrySet()){
            System.out.println(i.getKey());
        }

        System.out.println("TOOOOOOOOOP3");
        Map<Double,Hotel> TopDownF3=new TreeMap<>();
        Iterator<Map.Entry<Double,Hotel>> iterator=SortedBestHotels.entrySet().iterator();
        for(int i=0;iterator.hasNext();i++){
            Map.Entry<Double,Hotel> pair = iterator.next();
            if(i>=3)
            TopDownF3.put(pair.getKey(),pair.getValue());
            //System.out.println(pair.getValue().getName());
        }
        Map<Double,Hotel> TopDown3=new TreeMap<>(TopDownF3).descendingMap();



        if(comfort==0){
            model.addAttribute("hotelTotal",HotelsMapNoTotal);
        } else{
            model.addAttribute("hotelTotal",TopDown3);
        }

        System.out.println("TOOOOOOOOOP3");
        Map<Double,Hotel> TopF3=new TreeMap<>();
        Iterator<Map.Entry<Double,Hotel>> iterator2=SortedBestHotels.entrySet().iterator();
        for(int i=0;i<3&& iterator2.hasNext();i++){
            Map.Entry<Double,Hotel> pair = iterator2.next();
            TopF3.put(pair.getKey(),pair.getValue());
            //System.out.println(pair.getValue().getName());
        }
        Map<Double,Hotel> Top3=new TreeMap<>(TopF3).descendingMap();

        model.addAttribute("topHotels",Top3);
        //model.addAttribute("hotels", hotelRepository.findBestHotels(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1, 1,1,1,1,1));
        //model.addAttribute("hotels",hotelRepository.findAll());

        boolean auth = SecurityContextHolder.getContext().getAuthentication().getName()=="anonymousUser";
        model.addAttribute("auth",!auth);

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //System.out.println(name);
        //System.out.println(auth);

        if(authentication!=null){
            Optional<User> user = userRepository.findByEmail(authentication.getName());
            System.out.println("userid"+(user.get().getId()));
            model.addAttribute("recommededHotels",recommendedHotels);
        } else {
            model.addAttribute("recommededHotels",recommendationService.getRecommendedHotels(4));
        }

        System.out.println("rec:");
        for(Hotel i:recommendedHotels){
            System.out.println(i.getName());
        }

        if(comfort==0){
            int totalCounter=1;
            model.addAttribute("totalCounter",totalCounter);
        } else{
            int totalCounter=4;
            model.addAttribute("totalCounter",totalCounter);
        }



        return "index";
    }


    @GetMapping("/hotel")
    public String hotel(@RequestParam(value = "id") int id, Model model){
        System.out.println("get mappint hotel-----------------------");

        model.addAttribute("hotel",hotelRepository.getReferenceById(id));
        boolean auth = SecurityContextHolder.getContext().getAuthentication().getName()=="anonymousUser";
        model.addAttribute("auth",!auth);

        model.addAttribute("residenceHistory",hotelRepository.getReferenceById(id).getResidenceHistoryList());


        ResidenceHistory new_residenceHistory= new ResidenceHistory();
        model.addAttribute("new_residenceHistory",new_residenceHistory);

        //residenceHistoryRepository.save(new_residenceHistory);

        System.out.println("get mappint hotel-----------------------");

        System.out.println("RAAAAAAAAAAAAAAAAAtings:");
        //System.out.println((residenceHistoryRepository.findRatings().get(0))[2]);
        //System.out.println(aggregatedDataRepository.findRatings());
        //System.out.println(residenceHistoryRepository.findRatings().get(0));
        for(Object[] obj : residenceHistoryRepository.findRatings()){

            /*System.out.println(obj[0]);
            Integer a = (Integer) obj[0];
            System.out.println();
            System.out.println(a);*/

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
        //int hotelId=new_residenceHistory.getHotel_rev().getId();

        System.out.println("HotelId:");
        System.out.println(hotelId);
        Optional<User> user = userRepository.findByEmail(authentication.getName());

        //ResidenceHistory new_residenceHistory2= new ResidenceHistory(new_residenceHistory.getId(), new_residenceHistory.getCheckInDate(),new_residenceHistory.getCheckOutDate(), new_residenceHistory.getTotalCost(), new_residenceHistory.getReview(), new_residenceHistory.getGrade(), new_residenceHistory.getUsers_rev(),new_residenceHistory.getHotel_rev());

        //new_residenceHistory2=new_residenceHistory;
        //model.addAttribute("new_residenceHistory",new_residenceHistory);

        new_residenceHistory.setHotel_rev(hotelRepository.getReferenceById(hotelId));
        new_residenceHistory.setUsers_rev(user.get());


        //residenceHistoryRepository.save(new_residenceHistory);
        //System.out.println("hotel");
        //System.out.println(hotelRepository.getReferenceById(hotelId).getName());
        //System.out.println("User");
//        System.out.println(userRepository.getReferenceById(hotelId).getEmail());

        residenceHistoryRepository.insert(new_residenceHistory.getCheckInDate(),new_residenceHistory.getCheckOutDate(),
                new_residenceHistory.getTotalCost(),new_residenceHistory.getReview(),new_residenceHistory.getGrade(),user.get().getId(),hotelId);

        //newResidenceHistoryService.add_new(new_residenceHistory);

        //residenceHistoryRepository.save();
        System.out.println("---------------------------------------------");
        return "redirect:/hotel?id="+hotelId;
    }




}
