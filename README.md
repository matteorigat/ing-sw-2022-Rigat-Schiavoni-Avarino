
# Software Engineering final project

**Eriantys** is the final project of **Software Engineering** course held
at Politecnico di Milano. (2021/2022)  

**Teacher** Gianpaolo Cugola

![Image of the game](src/main/resources/Graphics/eriantysBackground.jpg)

## Implemented Functionalities
| Functionality | Status |
|:-----------------------|:------------------------------------:|
| Basic rules | ✅ |
| Complete rules | ✅ |
| Socket | ✅ |
| CLI | ✅ |
| GUI | ✅ |
| Multiple games | ✅ |
| 12 character cards | ✅ |

#### Legend
✅ Implemented

## Running
To launch the game use the following command in the directory of the jar.

```
java -jar mac_arm.jar 
```

```
java -jar mac_intel.jar 
```

```
java -jar win.jar 
```

If you don't have one of the above operating system, [download javafx 18](https://gluonhq.com/products/javafx/) and use the following command in the directory of the jar (one of the three, is the same) and the directory you already downloaded

```
java --module-path \Users\.."complete here"..\javafx-sdk-18.0.1/lib --add-modules=javafx.controls,javafx.fxml,javafx.media -jar \Users\.."complete here"..\win.jar
```

##

You'll have to choose if you want to run the server, CLI client or GUI client.

If you use the default configuration on the server the port is 50000.
If you use the default configuration on the client the ip is 127.0.0.1 and the port is 50000.

The recommended setting for the GUI are : 
* Resolution 1440x900  (13inches at least)

## Authors
* [Matteo Rigat]([https://github.com/](https://github.com/MatteoRigat))
* [Nicolò Avarino]([https://github.com/neekoo0](https://github.com/nicoloavarino))
* [Giuseppe Schiavoni]([https://github.com/fraleone99](https://github.com/Giuseppe-Schiavoni))

# Tools
* Maven - Dependency Management
* Intellij - IDE
* JavaFX - GUI

## License
This project is developed in collaboration with [Politecnico di Milano](https://www.polimi.it/) and [Cranio Creations](https://www.craniocreations.it/)

