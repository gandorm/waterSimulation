# waterSimulation

tak powinien wyglądać wasz .classpath:

<?xml version="1.0" encoding="UTF-8"?>
<classpath>
	<classpathentry kind="src" path="src"/>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER"/>
	<classpathentry kind="lib" path="lib/jars/AppleJavaExtensions.jar"/>
	<classpathentry kind="lib" path="lib/jars/asm-debug-all.jar"/>
	<classpathentry kind="lib" path="lib/jars/jinput.jar"/>
	<classpathentry kind="lib" path="lib/jars/laf-plugin-7.2.1.jar"/>
	<classpathentry kind="lib" path="lib/jars/laf-widget-7.2.1.jar"/>
	<classpathentry kind="lib" path="lib/jars/log4j-api-2.0-beta9.jar"/>
	<classpathentry kind="lib" path="lib/jars/lwjgl_test.jar"/>
	<classpathentry kind="lib" path="lib/jars/lwjgl_util_applet.jar"/>
	<classpathentry kind="lib" path="lib/jars/lwjgl_util.jar"/>
	<classpathentry kind="lib" path="lib/jars/lwjgl-debug.jar"/>
	<classpathentry kind="lib" path="lib/jars/lwjgl.jar">
		<attributes>
			<attribute name="org.eclipse.jdt.launching.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY" value="waterSimulation/lib/natives"/>
		</attributes>
	</classpathentry>
	<classpathentry kind="lib" path="lib/jars/lzma.jar"/>
	<classpathentry kind="lib" path="lib/jars/slick-util.jar"/>
	<classpathentry kind="lib" path="lib/jars/substance-7.2.1.jar"/>
	<classpathentry kind="lib" path="lib/jars/trident-7.2.1.jar"/>
	<classpathentry kind="output" path="bin"/>
</classpath>


tak srednio sie zaciagaja te natives wiec tak:
http://sourceforge.net/projects/java-game-lib/?source=typ_redirect

sciagacie to
potem w folderze lib w projekcie tworzycie sobie podfolder natives
wrzucacie tam natives z tego sciagnietego rara
Configure Build Path -> Libraries -> Dwuklik na lwjgl.jar -> i tam jest opcja natives gdzie wybieracie ten folder z przekopiowanymi .dllami

Potem refresh na caym projekcie i powinno dzialac w pytke ;)
