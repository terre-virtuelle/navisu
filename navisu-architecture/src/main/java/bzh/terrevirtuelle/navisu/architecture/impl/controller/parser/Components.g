grammar Components;

@header{
package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;
}
@lexer::header{
package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;

import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.PrintComponentHandler;
import bzh.terrevirtuelle.navisu.domain.architecture.Component;
import java.util.regex.Pattern;
}
@rulecatch {
  catch (RecognitionException e) {
  //System.out.println("eeeeee"+e.toString());
    throw e;
   }
}

@parser::members { 
    @Override    
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        throw new RuntimeException(hdr + ":" + msg);
    }
}

@lexer::members{

   protected Component component;

   /* Default handlers */
   protected Handler handler = new PrintComponentHandler();

   public void setHandler(Handler handler){
   this.handler = handler;
   }
   
   
   protected void mismatch(IntStream input, int ttype, BitSet follow)
   throws RecognitionException {
        System.out.println("mismatch");
       throw new MismatchedTokenException(ttype, input);
    }

    public Object recoverFromMismatchedSet(IntStream input,
                          RecognitionException e, BitSet follow) throws RecognitionException {
        System.out.println("recoverFromMismatchedSet");
       throw e;
    }
   
}	
entry 	:    (PRE|NAME|IMPLEMENTATION|STATE|SERVICE_PROVIDED|EVENT_PROVIDED|USED_SERVICES
|CONSUMED_EVENT|USED_EVENT_SUBSCRIBE|SUB_COMPONENT|COMPONENT_ITEM|POST|SEPARATOR)+;

PRE	: '<?xml version="1.0" encoding="UTF-8" standalone="no"?>' (LETTERS|NUMBERS|'\n')* 
{
//handler.doIt(getText());
};
SEPARATOR	:	('='|'-'|' ')* '\n'
{
//handler.doIt(component);

};
NAME    : 'COMPONENT NAME : ' (LETTERS|NUMBERS)* '\n'
        {
        String [] name0 =getText().split(Pattern.quote("."));
        String [] name1 = name0[name0.length-1].split(" ");
        component = new Component(name1[0].trim());
        String[] l = name1[name1.length-1].split(":");
        int level = Integer.parseInt((l[l.length-1]).trim());
        component.setLevel(level);
       
        };

        		
IMPLEMENTATION    : 'IMPLEMENTATION : ' impl=LETTERS* '\n'
        {
     
        // System.out.println("IMPLEMENTATION : " + getText());
        String[] impl0 = impl.getText().trim().split(" ");
        String[] impl1 = impl0[impl0.length-1].split(Pattern.quote("."));
        component.setImplementation(impl1[impl1.length-1].trim());
       // handler.doIt(component);
        };
STATE    : 'STATE :'LETTERS* '\n'*
        {
         String [] name0 =getText().split(Pattern.quote(":"));
         String [] name1 =name0[name0.length-1].trim().split(Pattern.quote("."));
       // System.out.println("STATE : " + getText());
         component.setState(name1[name1.length-1].trim());
       //  handler.doIt(component);
        };
SERVICE_PROVIDED    : (('SERVICE PROVIDED:'WS)| (WS LETTERS))*
        {
        component.addServiceProvided(getText());
      //  handler.doIt(component);
        };
EVENT_PROVIDED    : 'EVENT PROVIDED:' WS//(' '|'\t'|'\n' INTERFACE)*
        {
       // handler.doIt(getText());
       // System.out.println("EVENT_PROVIDED : " + getText());
        component.addEventProvided(getText());
     // handler.doIt(component);
        };
USED_SERVICES   : 'USED SERVICES :' WS //INTERFACE*
        {
        //handler.doIt(getText());
      //  System.out.println("USED_SERVICES : " + getText());
        component.addUsedService(getText());
      //  handler.doIt(component);
        };
CONSUMED_EVENT    : 'CONSUMED EVENT:' WS //CLASS*
        {
       
      //   System.out.println("CONSUMED_EVENT : " + getText());
        component.addConsumedEvent(getText());
      //  handler.doIt(component);
        };
USED_EVENT_SUBSCRIBE : 'USED EVENT SUBSCRIBE:' WS
        {
      // handler.doIt(component);
        // System.out.println("USED_EVENT_SUBSCRIBE : " + getText());
       
        };
        
SUB_COMPONENT    : 'SUB COMPONENT:' WS 
        {
        handler.doIt(component);
        // System.out.println("SUB_COMPONENT : " + getText());
        };
        
COMPONENT_ITEM    : 'COMPONENT ITEM:' LETTERS WS
        {
     //  handler.doIt(component);
        };
        
INTERFACE 	: 'interface' LETTERS WS
        {
        //System.out.println(getText());
        };
        
CLASS 	: (' '|'\t')'class ' (LETTERS*~('class')) '\n'
        {
        };
       
IMPL		: '\t'LETTERS'Impl' '\n'
        {
      //  System.out.println(getText());
        };
POST	:	'</message>' (LETTERS|NUMBERS|'\n')* 
{

};

 LETTERS : (('A'..'Z')|('a'..'z')|'.'|':'|' '|'<'|'!'|'?'|'>'|'/'|'"'|'-'|'\t')+
 {
 //System.out.println(getText());      
  };      
NUMBERS	:  
    '0'..'9'+
    |
    ('0'..'9')+ '.' ('0'..'9')*
   ;//{System.out.println(getText());};
WS
    : (' '|'\t'|'\n'|'\r')+ {skip();}
    ;
