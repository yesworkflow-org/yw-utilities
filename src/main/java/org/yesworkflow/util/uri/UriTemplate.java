package org.yesworkflow.util.uri;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Pattern;

import org.yesworkflow.util.io.FileIO;

/**
 * <p> This class represents a URI template that can be matched
 * directly against other URI templates without expanding 
 * either template to a concrete URI.  Two URI templates match
 * if the path portions are identical other than the names
 * given to variables in the template.  I.e., for two templates to match,
 * the <i>positions</i> of the variables in the templates must match, 
 * but the <i>names</i> of the 
 * variables need not match.  Variable names also may be completely left 
 * out the templates, with variable positions represented by empty 
 * pairs of curly braces. </p>
 * 
 * This file is derived from UriTemplate.java in the org.restflow.data package
 * as of 28Apr2015.
 */
public class UriTemplate extends UriBase {

    private static Long nextUriVariableId = 1L;
    
	///////////////////////////////////////////////////////////////////
	////                    private data fields                    ////

	public final String reducedPath;       // Fully reduced, directly matchable representation of the URI template
	public final Path leadingPath;         // Portion of path preceding any path element that contains variables
	public final UriTemplateVariable[] variables;  // Array of uri variables named in the URI template in order of their first appearnce
    public final UriTemplateVariable[] instances;   // Array of references to URI variables in order of each occurrence in the template
	public final String[] fragments;       // Array of strings representing non-variable portions of the template path
	
	///////////////////////////////////////////////////////////////////
	////                     public constructors                   ////

	/**
	 * Creates a new MatchableUriTemplate object.
	 * 
	 * @param template	A string representation of the full URI template to construct.
	 */
	public UriTemplate(String template) {

		super(template, true);

		// store a reduced version of the path with the variable names deleted
		// and extract the variable names and fixed portions of the template path
		List<String> variableNames = new LinkedList<String>();
		List<String> constantFragments = new LinkedList<String>();
		reducedPath = reduceTemplateAndExtractVariables(path, variableNames, constantFragments);
		instances = new UriTemplateVariable[variableNames.size()];
		Map<String,UriTemplateVariable> uriVariableForName = new LinkedHashMap<String,UriTemplateVariable>();
		int position = 0;
		for (String name : variableNames) {
		    UriTemplateVariable uriVariable = uriVariableForName.get(name);
		    if (!name.isEmpty()) {
    		    if (uriVariable == null) {
    		        uriVariable = new UriTemplateVariable(nextUriVariableId++, name);
    		        uriVariableForName.put(name, uriVariable);
    		    }
		    }
		    instances[position++] = uriVariable;
		}
		
		variables = new UriTemplateVariable[uriVariableForName.size()];
		int i = 0;
		for (Map.Entry<String, UriTemplateVariable> entry : uriVariableForName.entrySet()) {
		    variables[i++] = entry.getValue();
		}

		// store the fixed portions of the template path as an array
		fragments = constantFragments.toArray(new String[] {});
		
		String leadingPathString;
		if (fragments.length == 0) {
		    leadingPathString = "";
		} else if (variables.length == 0) {
		    leadingPathString = fragments[0];
		} else {
		    int lastSlashInFirstFragment = fragments[0].lastIndexOf('/');
		    if (lastSlashInFirstFragment == -1) {
		        leadingPathString = "";
		    } else {
		        leadingPathString = path.substring(0, lastSlashInFirstFragment);		        
		    }
		}
		
		leadingPath = Paths.get(leadingPathString);
	}

	//TODO Add support for double slashes following scheme delimiter.
	
	///////////////////////////////////////////////////////////////////
	////                 public instance methods                   ////

	public String getGlobPattern() {
        StringBuilder globPatternBuilder = new StringBuilder("glob:");
        globPatternBuilder.append(fragments[0]);
        for (int i = 0; i < fragments.length - 1; i++) {
            globPatternBuilder.append("*");
            globPatternBuilder.append(fragments[i+1]);
        }
        return globPatternBuilder.toString();
    }

	public Pattern getRegexpPattern() {
	    StringBuilder regexpPatternBuilder = new StringBuilder("");
        regexpPatternBuilder.append(fragments[0]);
        for (int i = 0; i < fragments.length - 1; i++) {
            regexpPatternBuilder.append("[\\S]+");
            regexpPatternBuilder.append(fragments[i+1]);
        }
        Pattern pattern = Pattern.compile(regexpPatternBuilder.toString());
        return pattern;
    }
	
    public Map<String,String> extractValuesFromPath(String path) throws Exception {

        path = FileIO.normalizePathSeparator(path);
        Map<String,String> variableValues = new LinkedHashMap<String,String>();

       // TODO handle case where first variable precedes first constant fragment
       // TODO handle case where last variable follows last constant fragment
       int start = 0;
       for (int i = 0; i < fragments.length - 1; ++i) {
           int valueStart = start + fragments[i].length();
           int valueEnd = path.indexOf(fragments[i+1], valueStart);
           if (valueEnd == -1) throw new Exception("Cannot find fragment '" + fragments[i+1] + "' in '" + path.substring(valueStart));
           UriTemplateVariable variable = instances[i];
           // TODO make sure values in concrete URI match for multiple instances of a variable
           if (variable != null && variableValues.get(variable.name) == null) {
               String value = path.substring(valueStart, valueEnd);
               variableValues.put(variable.name, value);
               if (!variable.name.isEmpty()) {
                   variableValues.put(variable.name, value);
               }
           }
           start = valueEnd;
       }

       return variableValues;
  }

	/** @return The reduced, directly matchable representation of the URI template. */
	public String getReducedPath() {
		return reducedPath;
	}

	/** 
	 * @param otherTemplate The URI template to compare reduced path against.
	 * @return true if the reduced paths of the two templates are identical, otherwise false
	 */
	public boolean matches(UriTemplate otherTemplate) {
		return this.reducedPath.equals(otherTemplate.reducedPath);
	}
	
	/**
	 * Expands the path portion of a URI template given an array of variable name-value pairs.
	 * Also returns the values of the variables used in the template as an array ordered by
	 * appearance of the variables in the template.  Variables values will appear multiple times
	 * in the array if the corresponding variables are used more than once in the template.
	 * 
	 * @param nameValueMap	Mapping of variable names to values to be used
	 * 						when expanding the template.
	 * @param valueArray	Reference used to return an array of the values of the 
	 * 						template variables expanded, in order of appearance
	 * 						in the template.
	 * @return				String containing the requested expansion of the path portion of the URI template.
	 * @throws				Exception if template has missing variables, i.e. empty braces
	 * 						and thus cannot be expanded.
	 */
	public String getExpandedPath(Map<String,Object> nameValueMap, Object[] valueArray) throws Exception {
		
		// create a string builder for assembling the expanded template path
		// starting with the first constant fragment of the template path
		StringBuilder expandedPathBuilder = new StringBuilder(fragments[0]);
		
		// iterate over template variable indices
		for (int i = 0; i < variables.length; i++) {
			
			// get the next variable name and make sure it isn't an empty string
			String name = variables[i].name;
			if (name.isEmpty()) {
				throw new Exception("Cannot expand a URI template with missing variable names: " 
						+ expression);
			}
			
			// look up the variable value in the value map and make sure there is one
			Object value = nameValueMap.get(name);
			if (value == null) {
				throw new Exception ("Could not expand template. No value for variable '" + name + "'.");
			}
			
			// hex-encode any characters not safe to use in file system patha
			String encodedValue = encodeString(value.toString());
			
			// append the value of the current variable to the expanded path
			expandedPathBuilder.append(encodedValue);
			
			// append the next constant fragment of the template path
			expandedPathBuilder.append(fragments[i+1]);

			// save the value of the current variable in the value array
			valueArray[i] = value;
		}
		
		// return the expanded path
		return expandedPathBuilder.toString();
	}

	///////////////////////////////////////////////////////////////////
	////                  public class methods                     ////
	
	/**
	 * Extract variables names from URI template.
	 *  
	 * @param fullTemplate A URI template including variable names.
	 * @param variables A list in which the method stores the names of the variables extracted from the full template.
	 * @param fragments A list the constant fragments of the URI template.
	 * @return A reduced template with variable names removed.
	 */
	public static String reduceTemplateAndExtractVariables(String fullTemplate, List<String> variables, 
			List<String> fragments) {
		
		// create a buffer for composing the reduced template
		StringBuffer reducedPathBuffer = new StringBuffer();

		// iterate over paired braces in full template
		int openingBracePosition = 0;
		int closingBracePosition = 0;
		int fragmentStartOffset = 0;
		
		while ((openingBracePosition = fullTemplate.indexOf('{', closingBracePosition)) != -1) {
			
			// store substring between last closing brace and next opening brace as fixed path fragment
			fragments.add(fullTemplate.substring(closingBracePosition + fragmentStartOffset, 
					openingBracePosition));
			
			// add to reduced template substring starting at last closing brace through the next opening brace in full template
			reducedPathBuffer.append(fullTemplate.substring(closingBracePosition, openingBracePosition + 1));
			
			// find next closing brace in full template
			closingBracePosition = fullTemplate.indexOf('}', openingBracePosition);
			
			// save the variable name between the current pair of braces
			String variableName = fullTemplate.substring(openingBracePosition + 1, closingBracePosition);
			variables.add(variableName);
			
			// constant fragments begin one character after last closing brace after first pass through loop
			fragmentStartOffset = 1;
		}

		// store the final fixed path fragment
		fragments.add(fullTemplate.substring(closingBracePosition + fragmentStartOffset, 
				fullTemplate.length()));
		
		// add to the reduced template the portion of the full template that follows the final closing brace
		reducedPathBuffer.append(fullTemplate.substring(closingBracePosition, fullTemplate.length()));
		
		// return the reduced path
		return reducedPathBuffer.toString();
	}	
}
