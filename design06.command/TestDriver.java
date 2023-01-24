package design06.command;

/* command is function call */
/* e.g. x.move(10,20) to objectification */
/* e.g. [receiver].[functionPointer](data, data) */


/* class and algorithm provided from outside */
class Light {
    String name;

    Light(String name) {
        this.name = name;
    }
    void on() {
        System.out.println(name + " Light is on");
    }
    void off() {
        System.out.println(name + " Light is off");
    }
}
/**/

interface Command {
    void excute();
    void undo();
}

class LightOnCommand implements Command {
    Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }
    public void excute() {
        light.on();
    }
    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    Light light;

    LightOffCommand(Light light) {
        this.light = light;
    }
    public void excute() {
        light.off();
    }
    @Override
    public void undo() {
        light.on();
    }
}

/* for defense about null point exception */
class NoCommand implements Command {
    @Override
    public void excute() {
        System.err.println("slot is empty!");
    }

    @Override
    public void undo() {
        System.err.println("slot is empty!");        
    }
    
}

class MacroCommand implements Command {
    Command[] commands;
    
    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    @Override
    public void excute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].excute();            
        }
    }
    @Override
    public void undo() {
        for (int i = commands.length-1; i >= 0; i--) {
            commands[i].undo();            
        }
    }
}

/* without touching invoker class, add or subtract features */
class RemoteControl {
    Command[] onCommands; // remote control slot
    Command[] offCommands;

    Command undoCommand;

    RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        /* for defense about null point exception */
        /* init */
        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < offCommands.length; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
        /**/
    }

    void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    void onButtonWasPressed(int slot) {
        onCommands[slot].excute();
        /* undo */
        undoCommand = onCommands[slot];
    }
    void offButtonWasPressed(int slot) {
        offCommands[slot].excute();
        /* undo */
        undoCommand = offCommands[slot];
    }

    void undoButtonWasPressed() {
        undoCommand.undo();
    }
}

/* client */
class TestDriver {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("living room"); // receiver. e.g. light.on(), light.off()
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight); // "when it calls execute(), what is return?"
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl(); // invoker. e.g. slot.excute()
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);

        /* using remote control */
        remote.onButtonWasPressed(0);
        remote.offButtonWasPressed(0);
        /**/
        remote.undoButtonWasPressed();


        /* macro */
        Command[] arr1 = {livingRoomLightOn, livingRoomLightOn, livingRoomLightOn, livingRoomLightOn};
        Command[] arr2 = {livingRoomLightOff, livingRoomLightOff, livingRoomLightOff, livingRoomLightOff};

        MacroCommand macroOn = new MacroCommand(arr1);
        MacroCommand macroOff = new MacroCommand(arr2);

        remote.setCommand(1, macroOn, macroOff);
        /**/
        remote.onButtonWasPressed(1);
        remote.offButtonWasPressed(1);
        remote.undoButtonWasPressed();
    }
}