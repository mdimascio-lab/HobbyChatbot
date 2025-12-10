
public class Chatbot
{
    private String rememberedHobby = null;
    // Random responses:
    private String getRandomResponse() {
        String response = "";

        int whichResponse = (int) (Math.random() * 5); //I'm trying to randomize between picking a random response and asking about the hobby

        if ((whichResponse == 0) && (rememberedHobby != null)) {
            int whichHobbyResponse = (int)(Math.random()*3);
            if (whichHobbyResponse == 0) {
            response = "Earlier you mentioned wanting to start " + rememberedHobby + ". Have you made any progress on it?";}
            else if (whichHobbyResponse == 1) {
                response = "You said you wanted to start " + rememberedHobby + ", right? How's that going?";
            }
            else {
                response = "Remember you wanted to start " + rememberedHobby + "? How's it coming along?";
            }

        } else if (whichResponse != 0) {

            String[] randomResponse = {"Hmm, very interesting.", "Oh, I haven’t heard that before.", "Please, go on!", "Hmm...what else could you tell me about that?", "Tell me more!", "Wow I didn't know that!", "Fascinating!"};

            int whichRandomResponse = (int) (Math.random() * randomResponse.length);

            response = randomResponse[whichRandomResponse];
        }
        return response;
    }







    //Transformations:
    private String transformILoveStatement(String statement)
    {
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement.length() - 1);
        }
        int position = findPhrase (statement, "I love", 0);
        String restOfStatement = statement.substring(position + 7);
        return "You said you love '"+ restOfStatement + "'? What do you love about it?";
    }
    private String transformIWantToLearnStatement(String statement)
    {
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement.length() - 1);
        }
        int position = findPhrase (statement, "I want to learn", 0);
        String restOfStatement = statement.substring(position + 16);
        return "That sounds like a great idea! How can I help you learn " + restOfStatement + "?";
    }
    private String transformNeedHelpWithStatement(String statement)
    {
        String lastChar = statement.substring(statement.length() - 1);
        if (lastChar.equals("."))
        {
            statement = statement.substring(0, statement.length() - 1);
        }
        int position = findPhrase (statement, "need help with", 0);
        String restOfStatement = statement.substring(position + 15);
        return "You said you need help with '"+ restOfStatement + "'? What can I do for you?";
    }



// Responses to keywords:
    public String getResponse(String statement)
    {
        String response = "";
        if ((statement.indexOf("video games") >= 0) || (statement.indexOf("video game") >= 0))
        {
            response = "What is your favorite video game?";
        }
        else if ((statement.indexOf("draw") >= 0) || (statement.indexOf("drawing") >= 0) || (statement.indexOf("coloring") >= 0))
        {
            int drawResponseOption = (int) (Math.random()*2)+1;
            if  (drawResponseOption == 1) //randomized response to keyword
            {
                response = "Ohh, what do you like drawing the most?";
            }
            else{
                response = "Do you prefer coloring with colored pencils or markers?";
            }
        }
        else if ((statement.indexOf("sport") >= 0) || (statement.indexOf("sports") >= 0) || (statement.indexOf("working out") >= 0))
        {
            int sportResponseOption = (int) (Math.random()*2)+1;
            if  (sportResponseOption == 1) //randomized response to keyword
            {
                response = "Doing a sport is very healthy! What is your favorite sport?";
            }
            else{
                response = "What is your favorite way of staying active?";
            }
        }
        else if(statement.indexOf("instrument") >= 0){

            int instrumentResponseOption = (int) (Math.random()*2)+1;
            if  (instrumentResponseOption == 1) //randomized response to keyword
            {
                response = "Do you play any instruments?";
            }
            else{
                response = "What's your favorite instrument?";
            }
        }
        else if(statement.indexOf("book") >= 0 || statement.indexOf("reading") >= 0){

            int bookResponseOption = (int) (Math.random()*3)+1;
            if (bookResponseOption == 1){ //randomized response to keyword
                response = "What's your favorite book?";
            }
            else if(bookResponseOption == 2){
                response = "What's your favorite genre?";
            }
            else{
                response = "Do you read a lot?";
            }
        }
        else if(statement.indexOf("movie") >= 0) {
            int movieResponseOption = (int) (Math.random() * 2) + 1;

            if (movieResponseOption == 1) { //randomized response to keyword
                response = "What's your favorite movie?";
            } else {
                response = "Who's your favorite actor/actress?";
            }
        }
        else if(statement.indexOf("tv show") >= 0){
            int showResponseOption = (int) (Math.random() * 3) + 1;

            if (showResponseOption == 1) { //randomized response to keyword
                response = "What's your favorite tv show?";
            } else if(showResponseOption == 2) {
                response = "Who's your favorite actor/actress?";
            } else {
                response = "What types of tv shows do you prefer?";
            }

            }

        else if(statement.indexOf("music") >= 0 || statement.indexOf("song") >= 0){
            int musicResponseOption = (int) (Math.random()*3)+1;

            if (musicResponseOption == 1){ //randomized response to keyword
                response = "What's your favorite genre of music?";
            }
            else if (musicResponseOption == 2){
                response = "What's your favorite song?";
            }
            else {
                response = "Who's your favorite musical artist?";
            }
        }
        else if ((statement.indexOf("bored") >= 0) || (statement.indexOf("new hobby") >= 0)){

            //array for hobby options:
            String[] hobbyOptions = {"start a new tv show!", "play a video game!", "listen to new music!", "draw!", "try a new sport!", "learn to play an instrument!", "read a book!", "watch a movie!"};


            int whichHobbyOption = (int)(Math.random() * hobbyOptions.length); //randomized hobby option

            response = "You could " + hobbyOptions[whichHobbyOption];


        }

        // Responses which require transformations
        else if (findPhrase(statement, "need help with", 0) >= 0)
        {
            response = transformNeedHelpWithStatement(statement);
        }
        else if (findPhrase(statement, "I want to learn", 0) >= 0)
        {
            response = transformIWantToLearnStatement(statement);
        }
        else if (findPhrase(statement, "I love", 0) >= 0)
        {
            response = transformILoveStatement(statement);
        }

        //multi-turn context memory
        else if (statement.indexOf("I want to start") >= 0)
        {
            int hobbyPosition = statement.indexOf("I want to start");
            String hobby = statement.substring(hobbyPosition + 16);

            rememberedHobby = hobby;
            response = "Starting " + hobby + " sounds very fun!";
        }
        else
        {
            response = getRandomResponse();

        }

        return response;
    }

    private int findPhrase(String statement, String goal, int startPos)
    {
        String phrase = statement.trim().toLowerCase();
        goal = goal.toLowerCase();

        // The only change to incorporate the startPos is in
        // the line below
        int position = phrase.indexOf(goal, startPos);

        // Refinement--make sure the goal isn't part of a
        // word
        while (position >= 0)
        {
            // Find the string of length 1 before and after
            // the word
            String before = " ", after = " ";
            if (position > 0)
            {
                before = phrase.substring(position - 1, position);
            }
            if (position + goal.length() < phrase.length())
            {
                after = phrase.substring(position + goal.length(), position + goal.length() + 1);
            }

            // If before and after aren't letters, we've
            // found the word
            if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0)) // before is not a letter
                    && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0)))
            {
                return position;
            }
            // The last position didn't work, so let's find
            // the next, if there is one.
            position = phrase.indexOf(goal, position + 1);
        }
        return -1;
    }
    public String getGreeting()
    {
        return "Hey, let's talk about your hobbies! Type in \"bored\" or \"new hobby\" for hobby suggestions. " +
                "Type in \"I want to start\" followed by a hobby to be questioned about it later. " +
                "Type in \"Bye\" when you want to end our conversation.";
    }
}
