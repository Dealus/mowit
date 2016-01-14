package org.dealus.mowit.main

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j

import org.dealus.mowit.Lawn
import org.dealus.mowit.LawnFactory
import org.dealus.mowit.exceptions.LawnFactoryParsingException

/**
 * This main can be used to run this library main functionality
 * The objective is to give the position of a Mower after several movements on a grid called a Lawn.
 * @author Nicolas carlot
 *
 */
@Slf4j
@TypeChecked(TypeCheckingMode.SKIP)
class MowItNowMain {
	public static boolean debugMode = false

	private static List fileDescription = ['\t\t[Lawn size X (positive integer)] [Lawn size Y (positive integer)]',
		'\t\t[Mower1 position X (positive integer)] [Mower1 position Y (positive integer)] [Mower1 orientation (N/S/E/W)]',
		'\t[Mower1 directive(G: left, D: right, A: advance)]',
		'\t\t[Mower2 position X] (positive integer) [Mower2 position Y (positive integer)] [Mower2 orientation (N/S/E/W)]',
		'\t[Mower2 directive(G: left, D: right, A: advance)]']

	/**
	 * Run the mowit functionality over the given lawn and mowers definition
	 * @param content A string representing the definition of the lawn and its mowers
	 */
	public static void mowIt(String content) {
		try {
			Lawn lawn = LawnFactory.parse(content, debugMode)
			if(lawn) {
				lawn.runMowers()
				println lawn.getAllMowersPositions().join('\n')
			}
		}
		catch(LawnFactoryParsingException e) {
			
		}

	}

	public static void main(String[] args) {

		def cliBuilder = new CliBuilder(usage: '[-hrsd] [-f filePath]')
		cliBuilder.with {
			h longOpt: 'help', 'Show usage information'
			f longOpt: 'file', 'Input file path', args: 1
			s longOpt: 'showSample', 'Show an input file sample'
			r longOpt: 'runSample', 'Run against sample file'
			d longOpt: 'debug', 'Run in debug mode'
		}

		// No options given
		OptionAccessor options = cliBuilder.parse(args)
		if (!options) {
			return
		}

		// Debug mode
		if (options.d) {
			debugMode = true
		}
		
		// Show sample file
		if(options.s)  {
			InputStream sampleFile = ClassLoader.getClassLoader(MowItNowMain.class).getResourceAsStream("SampleFile.txt")
			List lines = sampleFile.readLines()
			lines.eachWithIndex { it, i ->
				println it + fileDescription[i]
			}
		}

		// Run the mowit functionality against the sample file
		if(options.r) {
			InputStream sampleFile = ClassLoader.getClassLoader(MowItNowMain.class).getResourceAsStream("SampleFile.txt")
			mowIt(sampleFile.getText())
		}
		
		// Run the mowit functionality against the given file
		if(options.f) {
			try {
				File file = new File(options.f)
				mowIt(file.getText())
			}
			catch(FileNotFoundException e) {
				log.info("File ${options.f} could not be found.")
				if(debugMode) {
					log.debug(e.message, e)
				}
			}
		}

		// Show help as last not to conflict with an empty option.f call
		if (options.h) {
			cliBuilder.usage()
		}
		
	}
}
