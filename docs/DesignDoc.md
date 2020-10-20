---
geometry: margin=1in
---
# PROJECT Design Documentation

## Team Information
* Team name: Waffles
* Team members
  * Beck Anderson
  * Dhruv Rajpurohit
  * Kelly Xiong Chen
  * Marcus Kapoor

## Executive Summary
This Project is creating a web based application called Webcheckers. Webcheckers is modeling a game of checkers on the web. To play the game the user must sign in with unique id. The user can compete in the game of checkers with other online players, in addition to that the user can spectate another game, help the player while spectating. Playing the game involves both players to move their piece one by one. The game governs by the American rules of checkers. 
 
### Purpose
The purpose of this project is create a web based application which follows the software development principles. The user goals of this project to provide a very efficient and exciting experience of the web based checkers game. The most important user groups of this project are the ones who have a similat interest towards the game of checkers.

### Glossary and Acronyms
> _Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| VO | Value Object |
| MVP | Minimum Viable Product |



## Requirements

This section describes the features of the application.

> _In this section you do not need to be exhaustive and list every
> story.  Focus on top-level features from the Vision document and
> maybe Epics and critical Stories._

### Definition of MVP
> _Provide a simple description of the Minimum Viable Product._

### MVP Features
> _Provide a list of top-level Epics and/or Stories of the MVP._

### Roadmap of Enhancements
> _Provide a list of top-level features in the order you plan to consider them._


## Application Domain

![The WebCheckers Web Interface Statechart](ApplicationTier.jpg)

The diagram is the domain model of the application and the fundamental 
structure of the web based application. In order for the application to 
function properly, it will be structured in a similar manner as depicted 
in the diagram. To play the game the user is supposed to have a unique 
username. The user can either be a spectator or play the game with 
another player. The game is played on the 8 by 8 board which consists of 
64 squares, either dark or light. Players have 12 checkers pieces each which 
are either red or black. The user is supposed to make a move on the dark 
square. Every move manipulates the checkers piece’s location on the square. 
The spectator can watch the game while two different players are playing 
the game and also has an option of suggesting a player of their choice 
with the next move. 


## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

As a web application, the user interacts with the system using a
browser.  The client-side of the UI is composed of HTML pages with
some minimal CSS for styling the page.  There is also some JavaScript
that has been provided to the team by the architect.

The server-side tiers include the UI Tier that is composed of UI Controllers and Views.
Controllers are built using the Spark framework and View are built using the FreeMarker framework.  The Application and Model tiers are built using plain-old Java objects (POJOs).

Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the WebCheckers application.

![The WebCheckers Web Interface Statechart](UserInterfaceTier.png)

The diagram shows the interface the user will experience while using the 
application. The interface has three main pages, they are the home page, 
the sign in page and the game page. The game starts by asking the user 
to sign in with a unique username. Post sign in the user is redirected 
to the home page where they can see the players online. The user can then
 challenge a player or wait to be challenged by another player. Once the 
 player has either challenged another player or is challenged by another 
 player, they are redirected to the game page where they can see the game 
 board. The player with red pisces makes the first move and then the other 
 player makes a move and this goes on until the game is over. If the player 
 has not made any kind of actions in a while, their session has timed out 
 and they are automatically redirected to the home page. 


### UI Tier
> _Provide a summary of the Server-side UI tier of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

![The UI Tier Sequence Diagram](ui-tier-seqDiagram.png)

> _At appropriate places as part of this narrative provide one or more
> static models (UML class structure or object diagrams) with some
> details such as critical attributes and methods._

> _You must also provide any dynamic models, such as statechart and
> sequence diagrams, as is relevant to a particular aspect of the design
> that you are describing.  For example, in WebCheckers you might create
> a sequence diagram of the `POST /validateMove` HTTP request processing
> or you might show a statechart diagram if the Game component uses a
> state machine to manage the game._

> _If a dynamic model, such as a statechart describes a feature that is
> not mostly in this tier and cuts across multiple tiers, you can
> consider placing the narrative description of that feature in a
> separate section for describing significant features. Place this after
> you describe the design of the three tiers._


### Application Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

![The Application Tier UML Diagram](AppTierStateChart.jpg)


### Model Tier
> _Provide a summary of the Application tier of your architecture. This
> section will follow the same instructions that are given for the UI
> Tier above._

![The Model Tier UML Diagram](ModelTierStateChart.jpg)

### Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements. After completion of the Code metrics exercise, you
> will also discuss the resutling metric measurements.  Indicate the
> hot spots the metrics identified in your code base, and your
> suggested design improvements to address those hot spots._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._
