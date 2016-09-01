package model.globals;

public abstract class Globals {
	
	/*
	 * Class Declaration
	 */
	public static XML xml;
	public static Actions actions;
	public static Paths paths;
	
	public static String AdCode = "<style>div.wrax { width: 100%; height: 100%; overflow: auto; }"
			+ "img.bg { min-height: 100%; min-width: 1024px; width: 100%; height: 100%; position: "
			+ "fixed; top: 0; left: 0; z-index: 0; }"
			+ "@media screen and (max-width: 1024px) { img.bg { left: 50%; margin-left: -512px; } }"
			+ "#mis { text-align: center; position: absolute; width: 100%; height: 100%; top: 0; "
			+ "left: 0; color: #fff; font-family: 'palatino linotype', palatino; font-weight: normal;"
			+ " text-align: left; text-shadow: 0 1px 0 #000; z-index: 10; }#mis .description "
			+ "{ position: relative; width: 580px; left: 50%; margin-left: -290px; }"
			+ "#mis .description h1 { font-size: 32px; }"
			+ "#mis .description .warning { color: #f00; }"
			+ "#mis .description .link, #mis .description a { font-size: 22px; color: #fff; text-decoration: underline; }"
			+ "#mis .description .footer, #mis .description .footer a { font-size: 10px; }</style>"
			+ "<span id='link' class='link'><!-- Assume Website CONTENT is HERE -->"
			+ "<div id='wrax' style='position: absolute; opacity: 0; filter: alpha(opacity = 50); "
			+ "margin-left: 9px; z-index: 100;'>"
			+ "**ADCODEHERE** </div><script type='text/javascript'>"
			+ "jQuery( document ).ready( function() {$('#link').mousemove( function( e ) {"
			+ " $( '#wrax' ).css( { top: e.pageY - 17, left: e.pageX - 8} );} );} )</script></span>";
}
