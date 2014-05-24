# January 2012 London Clojurians Clojure coding dojo - Battleships

Its not quite Global Thermo Nuclear War, but battleships was a great choice for a dojo topic. If you are not familiar with the Battleships game, please see the Wikipedia page on Battleships.

At the January 2012 dojo we used the battleships server created by ... and Robert Rees kindly facilitated the night. The battleships server allows you to submit your "player" and proceeds to play battleship games against is own player - CPU1 (Shame the player is not called Master Computer so I could slip in a tron reference).

To get started with the dojo I forked the project on Github to my own account and cloned the project repository to my local machine.  I am not that familiar with graphical git clients, so still use the command line to clone remote repositories, its pretty straight forward:

    git clone url local-folder-name

For my fork of the battleships game the command becomes

    git clone git@github.com:jr0cket/battleships.git jr0cket-battleships

When I clone a github repository that I have forked from someone elses repository I prefix the local folder name with my username so I know its my fork and not the original - saves a lot of hassle wondering why I cant push changes back to github directly :-)

Now the project is on my local computer I can fire up lein and get the project running.  First thing to do is to make sure I have all the libraries the project depends upon.  Leiningen will download the Internet of jars for me (just like maven) with the following command:

    lein deps

The battleships project uses Clojail to create a sandbox, so its important to set the Java runtime environment security permissions.  There is a handy lein task for this:

lein ...

Or you can just create the file with any handy text editor...

I then fire up emacs from within the top level project directory (makes it quicker to find my project files) and opened emacs with the project.clj file to see how the project is set up.

    emacs project.clj &

Clojure has a repl for working with the language dynamically, so I fire the REPL server up using emacs (of course).  Adding clojure-mode to emacs 24 gives you the swank REPL server - allowing you to call clojure-jack-in and fire up a swank REPl server using the lein project.clj project definition.  I defined a keyboard shortcut Ctrl-c, Ctrl-j for the clojure-jack-in command in the ...

I then open the relevant clojure code using the keyboard shortcut Ctrl-c Ctrl-f

For the dojo I just needed to work with the demo.clj file that defines a battleship player:  jr0cket-battleships/src/battleships/demo.clj
Once the project is loaded into emacs and swank is running, load the battleships namespace
into the swank server using the (use ...) function.

Note that (use) will ... whereas (requires) will also require you to add in extra stuff [JPS - look up the exact differences from the clojure docs]

user> (use :reload-all '[battleships.client :as client])
nil

For the dojo we had a central server that we all submitted to.  I also spun up a local battleships server so I could do some testing.

     lein ring server

Now I am in the namespace I can submit the player I created - initially this was just the default demo.clj player as I was interested in a baseline player to work with.  By default the demo.clj player will shoot and place your ships at random, with no intelligence to these
actions


    user> (submit-player "src/battleships/demo.clj" "baseline" "http://localhost:3000")
    Submitting to http://localhost:3000/create
    {:status 200, :headers {"date" "Tue, 31 Jan 2012 21:24:18 GMT", "content-type" "text/html; charset=utf-8", "connection" "close", "server" "Jetty(6.1.25)"}, :body "player1912"}


When submitting your "enhanced" player you should give it a name you will remember, different from other players.  As the game does not replay any matches, its probably worth submitting a new player rather than updating an existing one (there would have to be a lot of players to overload the server!).



    user> (submit-player "src/battleships/demo.clj" "Masher001" "http://localhost:3000")
    Submitting to http://localhost:3000/create
    {:status 200, :headers {"date" "Tue, 31 Jan 2012 21:24:18 GMT",
"content-type" "text/html; charset=utf-8", "connection" "close",
"server" "Jetty(6.1.25)"}, :body "player1912"}


If you are playing against each other in teams, then the server address will probably be the servers IP address (unless all the teams machines have a named alias for the server name).



## Emacs tip of the week
If you are using emacs to write the content for your blog you probably want to turn of the Fill minor mode that seems to be on by default when using Emacs 24 and the Emacs starter kit.  Easiest way for the new starter is to use the right mouse button to select the mode options on the control bar at the bottom of the buffer you are working in.

For those who enjoy the keyboard, fire up the macro auto-fill-mode

For those who would like to have a keyboard oriented lifestyle but are still learning emacs, press the key combination Meta-x and type in auto-fill-mode and press return.  This toggles the mode, so if its on it switches it of and vice versa.

> On Linux PC, Meta-x is the keyboard combination Alt-x and on MacOSX it is Ctrl-x (the apple control and not the other control key!)


## Avoid being an idiot

You need 5 good interactions to cancel out one negative one
Gentoo project lost 20% of their contributers by having a few total assholes and only when they were delt with did the project stop bleeding contributers

TCA - total cost of assholes

## How do you get things fixed in your project
- free hugs - personal interaction - personal interactions drive better communications 
- make it feel like actions will happen and people will stick around

Code is much easier to change than code

Thank you
[@jr0cket](https://github.com/jr0cket)
