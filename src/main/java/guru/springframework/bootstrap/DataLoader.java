package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadData();
    }

    private void loadData() {
        log.debug("Beginning loading data");
        saveGuacamole();
        saveTacos();
        log.debug("Finished loading data");
    }

    private void saveGuacamole() {
        Recipe guacamole = new Recipe();

        Category mexican = categoryRepository.findByDescription("Mexican").orElse(null);
        Set<Category> guacamoleCategories = new HashSet<>();
        guacamoleCategories.add(mexican);

        guacamole.setCategories(guacamoleCategories);

        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setPrepTime(10);
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setServings(3);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setSource("Simply Recipes");

        guacamole.addIngredient(new Ingredient("Avocado", BigDecimal.valueOf(2)));
        guacamole.addIngredient(new Ingredient("Kosher salt", BigDecimal.valueOf(0.5), unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null)));

        Ingredient limeJuice = new Ingredient();
        limeJuice.setDescription("Lime juice");
        limeJuice.setAmount(BigDecimal.valueOf(1));
        limeJuice.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        guacamole.addIngredient(limeJuice);

        Ingredient redOnion = new Ingredient();
        redOnion.setDescription("Red Onion");
        redOnion.setAmount(BigDecimal.valueOf(2));
        redOnion.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        guacamole.addIngredient(redOnion);

        Ingredient serranoChiles = new Ingredient();
        serranoChiles.setDescription("Serrano chiles");
        serranoChiles.setAmount(BigDecimal.valueOf(1));
        guacamole.addIngredient(serranoChiles);

        Ingredient cilantro = new Ingredient();
        cilantro.setDescription("Cilantro");
        cilantro.setAmount(BigDecimal.valueOf(2));
        cilantro.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        guacamole.addIngredient(cilantro);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setDescription("Black pepper");
        blackPepper.setAmount(BigDecimal.valueOf(1));
        blackPepper.setUom(unitOfMeasureRepository.findByDescription("Dash").orElse(null));
        guacamole.addIngredient(blackPepper);

        Ingredient tomato = new Ingredient();
        tomato.setDescription("Tomato");
        tomato.setAmount(BigDecimal.valueOf(0.5));
        guacamole.addIngredient(tomato);

        guacamole.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "Variations\n" +
                "\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Guacamole notes....");
        guacNotes.setRecipe(guacamole);
        guacamole.setNotes(guacNotes);

        recipeRepository.save(guacamole);
    }

    private void saveTacos() {
        Recipe tacos = new Recipe();

        Category mexican = categoryRepository.findByDescription("Mexican").orElse(null);
        Category fastFood = categoryRepository.findByDescription("Fast Food").orElse(null);
        Set<Category> tacosCategories = new HashSet<>();
        tacosCategories.add(mexican);
        tacosCategories.add(fastFood);

        tacos.setCategories(tacosCategories);

        tacos.setDifficulty(Difficulty.EASY);
        tacos.setPrepTime(20);
        tacos.setCookTime(15);
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setServings(5);
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setSource("Simply Recipes");

        Set<Ingredient> tacosIngredients = new HashSet<>();

        Ingredient anchoChiliPowder = new Ingredient();
        anchoChiliPowder.setDescription("Ancho chili powder");
        anchoChiliPowder.setAmount(BigDecimal.valueOf(2));
        anchoChiliPowder.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        anchoChiliPowder.setRecipe(tacos);
        tacosIngredients.add(anchoChiliPowder);

        Ingredient driedOregano = new Ingredient();
        driedOregano.setDescription("Dried oregano");
        driedOregano.setAmount(BigDecimal.valueOf(1));
        driedOregano.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null));
        driedOregano.setRecipe(tacos);
        tacosIngredients.add(driedOregano);

        Ingredient driedCumin = new Ingredient();
        driedCumin.setDescription("Dried cumin");
        driedCumin.setAmount(BigDecimal.valueOf(1));
        driedCumin.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null));
        driedCumin.setRecipe(tacos);
        tacosIngredients.add(driedCumin);

        Ingredient sugar = new Ingredient();
        sugar.setDescription("Sugar");
        sugar.setAmount(BigDecimal.valueOf(1));
        sugar.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null));
        sugar.setRecipe(tacos);
        tacosIngredients.add(sugar);

        Ingredient salt = new Ingredient();
        salt.setDescription("Salt");
        salt.setAmount(BigDecimal.valueOf(0.5));
        salt.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").orElse(null));
        salt.setRecipe(tacos);
        tacosIngredients.add(salt);

        Ingredient garlic = new Ingredient();
        garlic.setDescription("Garlic");
        garlic.setAmount(BigDecimal.valueOf(1));
        garlic.setUom(unitOfMeasureRepository.findByDescription("Clove").orElse(null));
        garlic.setRecipe(tacos);
        tacosIngredients.add(garlic);

        Ingredient orangeZest = new Ingredient();
        orangeZest.setDescription("Orange zest");
        orangeZest.setAmount(BigDecimal.valueOf(1));
        orangeZest.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        orangeZest.setRecipe(tacos);
        tacosIngredients.add(orangeZest);

        Ingredient orangeJuice = new Ingredient();
        orangeJuice.setDescription("Orange juice");
        orangeJuice.setAmount(BigDecimal.valueOf(3));
        orangeJuice.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        orangeJuice.setRecipe(tacos);
        tacosIngredients.add(orangeJuice);

        Ingredient oliveOil= new Ingredient();
        oliveOil.setDescription("Olive oil");
        oliveOil.setAmount(BigDecimal.valueOf(2));
        oliveOil.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").orElse(null));
        oliveOil.setRecipe(tacos);
        tacosIngredients.add(oliveOil);

        Ingredient chickenThighs = new Ingredient();
        chickenThighs.setDescription("Chicken thighs");
        chickenThighs.setAmount(BigDecimal.valueOf(4));
        chickenThighs.setRecipe(tacos);
        tacosIngredients.add(chickenThighs);

        Ingredient cornTortillas= new Ingredient();
        cornTortillas.setDescription("Corn tortillas");
        cornTortillas.setAmount(BigDecimal.valueOf(8));
        cornTortillas.setRecipe(tacos);
        tacosIngredients.add(cornTortillas);

        Ingredient arugula= new Ingredient();
        arugula.setDescription("Arugula");
        arugula.setAmount(BigDecimal.valueOf(8));
        arugula.setUom(unitOfMeasureRepository.findByDescription("Cup").orElse(null));
        arugula.setRecipe(tacos);
        tacosIngredients.add(arugula);

        Ingredient avocado= new Ingredient();
        avocado.setDescription("Avocado");
        avocado.setAmount(BigDecimal.valueOf(2));
        avocado.setRecipe(tacos);
        tacosIngredients.add(avocado);

        Ingredient radishes= new Ingredient();
        radishes.setDescription("Radishes");
        radishes.setAmount(BigDecimal.valueOf(4));
        radishes.setRecipe(tacos);
        tacosIngredients.add(radishes);

        Ingredient cherryTomatoes= new Ingredient();
        cherryTomatoes.setDescription("Cherry tomatoes");
        cherryTomatoes.setAmount(BigDecimal.valueOf(0.5));
        cherryTomatoes.setUom(unitOfMeasureRepository.findByDescription("Pint").orElse(null));
        cherryTomatoes.setRecipe(tacos);
        tacosIngredients.add(cherryTomatoes);

        Ingredient redOnion= new Ingredient();
        redOnion.setDescription("Red onion");
        redOnion.setAmount(BigDecimal.valueOf(0.25));
        redOnion.setRecipe(tacos);
        tacosIngredients.add(redOnion);

        Ingredient cilantro= new Ingredient();
        cilantro.setDescription("Cilantro");
        cilantro.setAmount(BigDecimal.valueOf(1));
        cilantro.setRecipe(tacos);
        tacosIngredients.add(cilantro);

        Ingredient sourCream= new Ingredient();
        sourCream.setDescription("Cilantro");
        sourCream.setAmount(BigDecimal.valueOf(0.5));
        sourCream.setUom(unitOfMeasureRepository.findByDescription("Cup").orElse(null));
        sourCream.setRecipe(tacos);
        tacosIngredients.add(sourCream);

        Ingredient lime= new Ingredient();
        lime.setDescription("Lime");
        lime.setAmount(BigDecimal.valueOf(1));
        lime.setRecipe(tacos);
        tacosIngredients.add(lime);

        tacos.setIngredients(tacosIngredients);
        tacos.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        recipeRepository.save(tacos);
    }
}
