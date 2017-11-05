grammar Components;

@header{
package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;


    }
@lexer::header{
package bzh.terrevirtuelle.navisu.architecture.impl.controller.parser;

import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.PrintHandler;
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
   
   protected String device;
   
   
   /* Default handlers */
   protected Handler handler = new PrintHandler();

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
entry 	:    (PRE|NAME|IMPLEMENTATION|STATE|SERVICE_PROVIDED
|EVENT_PROVIDED|USED_SERVICES
|CONSUMED_EVENT|USED_EVENT_SUBSCRIBE
|SUB_COMPONENT|COMPONENT_ITEM|POST|SEPARATOR)+;

PRE	: '<?xml version="1.0" encoding="UTF-8" standalone="no"?>' (LETTERS|NUMBERS|'\n')* 
{
handler.doIt(getText());
};
SEPARATOR	:	('='|'-'|' ')* '\n'
{
handler.doIt(getText());
};
NAME    : 'COMPONENT NAME : ' (component=LETTERS|NUMBERS)* '\n'
        {
       // handler.doIt(getText());
       System.out.println("COMPONENT : " + component.getText());
        };
IMPLEMENTATION    : 'IMPLEMENTATION : ' LETTERS* '\n'
        {
        handler.doIt(getText());
        // System.out.println("IMPLEMENTATION : " + getText());
        };
STATE    : 'STATE :'LETTERS* '\n'
        {
        handler.doIt(getText());
        // System.out.println("STATE : " + getText());
        };
SERVICE_PROVIDED    : 'SERVICE PROVIDED:' (LETTERS |'\n')*
        {
        handler.doIt(getText());
        // System.out.println("SERVICE_PROVIDED : " + getText());
        };
EVENT_PROVIDED    : 'EVENT PROVIDED:' LETTERS* '\n'
        {
        handler.doIt(getText());
        // System.out.println("EVENT_PROVIDED : " + getText());
        };
USED_SERVICES   : 'USED SERVICES :'LETTERS '\n'
        {
        handler.doIt(getText());
        // System.out.println("USED_SERVICES : " + getText());
        };
CONSUMED_EVENT    : 'CONSUMED EVENT:'LETTERS '\n'
        {
        handler.doIt(getText());
        // System.out.println("CONSUMED_EVENT : " + getText());
        };
USED_EVENT_SUBSCRIBE  : 'USED EVENT SUBSCRIBE:'LETTERS '\n'
        {
        handler.doIt(getText());
        // System.out.println("USED_EVENT_SUBSCRIBE : " + getText());
        };
SUB_COMPONENT    : 'SUB COMPONENT:'LETTERS '\n'
        {
        handler.doIt(getText());
        // System.out.println("SUB_COMPONENT : " + getText());
        };
COMPONENT_ITEM    : 'COMPONENT ITEM:'LETTERS '\n'
        {
        handler.doIt(getText());
        // System.out.println("COMPONENT_ITEM : " + getText());
        };
POST	:	'</message>' (LETTERS|NUMBERS|'\n')* 
{
handler.doIt(getText());
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

