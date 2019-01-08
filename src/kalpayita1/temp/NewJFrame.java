/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalpayita1;

import edu.stanford.nlp.coref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.coref.data.CorefChain.CorefMention;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.scenegraph.SemanticGraphEnhancer;
import edu.stanford.nlp.semgraph.semgrex.SemgrexPattern;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.semgraph.SemanticGraphFactory;
import edu.stanford.nlp.semgraph.semgrex.SemgrexMatcher;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.*;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Ashish
 */
public class NewJFrame extends javax.swing.JFrame {
    ArrayList<Description> descriptions;
    ArrayList<Scene> scenes;
    ArrayList<String> nouns;
    ArrayList<String> objectCategories;
    ArrayList<String> attributes;
    ArrayList<String> adjectives;
    ArrayList<String> otherNouns;
    ArrayList<String> standardRelations;
    HashMap<String,String> relationsMapping;
    HashMap<String,ArrayList<String[]>> relations;
    HashMap<String, String> objAttrPair;
    HashMap<String, String[]> objAttrWithType;
    String[] toBeObjects, toBeObjectsPlural;
    Properties props;
    StanfordCoreNLP pipeline;
    Annotation document;
    WordNet net;
    String scene_type = null;
    String displayString = "Output:...\n\n";
    Node root;
    HashMap<String,Node> nodes;
    HashMap<Integer, ArrayList<Node>> levelNodes; 
    
    
    
    ArrayList<String> seed_statements;
    ArrayList<String> bedroom_sentences;
    ArrayList<String> living_room_sentences;
    ArrayList<String> office_sentences;
    HashMap<String,String> bedroom_support;
    HashMap<String,String> living_room_support;
    HashMap<String,String> office_support;
    ArrayList<String> scene_types;
    HashMap<String, String> supportObjects;
    String inferObjsLivingRoom[]={"table","chair"};
    String inferObjsBedRoom[]={"bed","nightstand"};
    String inferObjsOfficeRoom[]={"desk","chair"};
    HashMap<String, String> sentenceSceneTypePair;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        initCoreVariables();
        initVariables();
    }
    private void initCoreVariables(){
        props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
        pipeline = new StanfordCoreNLP(props);
        net = new WordNet("src\\resources\\WordNet\\properties.xml");
        toBeObjects = new String[]{"wall", "notepad", "side-table", "sandwich", "flower-pot", "wall-hanging", "plate", "bed"};
        toBeObjectsPlural = new String[]{"walls", "notepads", "side-tables", "sandwiches", "flower-pots", "wall-hangings", "plates", "beds"};
        
        descriptions = new ArrayList<>();
        scenes = new ArrayList<>();
        living_room_sentences = new ArrayList<>();
        bedroom_sentences = new ArrayList<>();
        office_sentences = new ArrayList<>();
        living_room_support = new HashMap<>();
        bedroom_support = new HashMap<>();
        office_support = new HashMap<>();
        standardRelations = new ArrayList<>();
        initializeScenesAndDescriptions();
    }
    private void initVariables(){
        nouns = new ArrayList<>();
        objectCategories = new ArrayList<>();
        adjectives = new ArrayList<>();
        otherNouns = new ArrayList<>();
        attributes = new ArrayList<>();
        relations = new HashMap<String, ArrayList<String[]>>(); //<relation, {object1, object2}>
        objAttrPair = new HashMap<>();
        objAttrWithType = new HashMap<String, String[]>(); //<object, {attribute, attribute-type}>
        root = null;
        nodes = new HashMap<>();
        levelNodes = new HashMap<Integer, ArrayList<Node>>();
        
        scene_type = "bedroom"; //"bedroom" just for initialisation purpose, changed later
        relationsMapping = new HashMap<>();
        
        scene_types = new ArrayList<>();
        supportObjects= new HashMap<String, String>();
        sentenceSceneTypePair = new HashMap<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Perform Tasks");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        initVariables();
        try{
            
           
            /*String statements[]={"The chair is made of wood", 
			"The chair is red", 
			"The table is next to the chair",
			"There is a table next to a chair", 
			"Left of the chair",
			"The left chair",
			"The lamp is on the table",
			"The table is to the left of the chair",
			"There is a chair in the room",
                        "The teapot is on the table",
                        "There is a painting on the wall"};*/
            String text = jTextField1.getText();
            doWork(text);
            //doWork("The chair is made of wood and the desk is made of fan. The chair is red in color. The chair is next to the desk.");
            //for(int i=0;i<statements.length;i++)
                //doWork(statements[i]);
            /*JOptionPane.showMessageDialog(null, descriptions.get(0).description+" "+descriptions.get(0).descriptionId+" "+descriptions.get(0).sceneId, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, scenes.get(0).sceneId+" "+scenes.get(0).description, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);*/
            /*CoreDocument document = new CoreDocument(descriptions.get(0).description);
            pipeline.annotate(document);
            
            document.tokens();*/
            
            /*// list of ner tags for the second sentence
            CoreSentence sentence = document.sentences().get(0);
            List<String> posTags = sentence.nerTags();
            System.out.println("Example: ner tags");
            //JOptionPane.showMessageDialog(null, description+"\n"+sceneId, "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(posTags);
            System.out.println();
            
            // constituency parse for the second sentence
            Tree constituencyParse = sentence.constituencyParse();
            System.out.println("Example: constituency parse");
            System.out.println(constituencyParse);
            System.out.println();*/
            
            // dependency parse for the second sentence
            /*SemanticGraph dependencyParse = sentence.dependencyParse();
            System.out.println("Example: dependency parse");
            System.out.println(dependencyParse);
            System.out.println();*/
        }catch (Exception e) {
            e.printStackTrace();
        } 
    }//GEN-LAST:event_jButton1ActionPerformed
    private void doWork(String text){
        sceneParsing(text);
        displayParsing();
        sceneInference(text);
        displayInference();
        createSceneGraph();
        displaySceneGraph();
        
    }
    public void semgrexPart(List<CoreMap> sentences){
        for(CoreMap sentence: sentences) {
            // this is the parse tree of the current sentence
            Tree tree = sentence.get(TreeAnnotation.class);
            System.out.println("tree: "+tree);
         // String [] patterns={"{}=pred >nsubj {tag:/NNP?S?/}=subj >/(iobj|dobj|nmod:.*)/=objreln {tag:/NNP?S?/}=obj !> cop {}"};
            
            String[] patterns = {"{tag:VBN}=verb >nsubjpass {}=nsubj >prep ({}=prep >pobj {}=pobj)", 
			"{}=dobj >cop {} >nsubj {}=nsubj",
			"{}=dobj >cop {} >nsubj {}=nsubj >prep ({}=prep >pobj {}=pobj)",
			"{}=nsubj >advmod ({}=advmod >prep ({}=prep >pobj {}=pobj))", 
			"{word:is} >nsubj {}=nsubj >prep ({}=prep >pobj {}=pobj)",
			"{word:is} >nsubj {}=nsubj >prep ({}=prep >pobj {}=pobj)"};
         //   String [] patterns={""};
			
          //  SemanticGraph graph = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
	    SemanticGraph graph = SemanticGraphFactory.generateUncollapsedDependencies(tree);

        /*    SemanticGraphEnhancer.processQuanftificationModifiers(graph);
            SemanticGraphEnhancer.collapseCompounds(graph);
            SemanticGraphEnhancer.collapseParticles(graph);
            SemanticGraphEnhancer.resolvePronouns(graph);
			*/
                SemgrexPattern semgrex;
                SemgrexMatcher matcher;
                /*semgrex = SemgrexPattern.compile(patterns[0]);
                //System.out.println("Pattern: "+semgrex.pattern()+"  ....");
                matcher = semgrex.matcher(graph);
                if (matcher.find()) {
                     //   System.out.println("subj "+matcher.getNode("subj").word()+" obj "+matcher.getNode("obj").word()+" pred "+matcher.getNode("pred").word());
                    String propertyWord = matcher.getNode("pobj").word();//send pobj to get the property of the word extracted here
			String nsubj = matcher.getNode("nsubj").word();
			String property="";
			if(shapeFinding(propertyWord))
                            property="shape";
			else if(colorFinding(propertyWord))
                            property="color";
			else if(patternFinding(propertyWord))
                            property="pattern";
			else if(materialFinding(propertyWord))
                            property="material";
			System.out.println("1. "+property+"(" + nsubj + "," + propertyWord + ")");
                        //System.out.println(matcher.getNode("dobj") + "(" + matcher.getNode("nsubj") + "," + matcher.getNode("pobj") + ")");
                    }*/
                
                    semgrex = SemgrexPattern.compile(patterns[1]);
                    System.out.println("Pattern: "+semgrex.pattern()+"  ....");
                    matcher = semgrex.matcher(graph);
                    if(matcher.find())
                    {
			String propertyWord = matcher.getNode("dobj").word();//send dobj to get the property of the word extracted here
			String nsubj = matcher.getNode("nsubj").word();
			String property="";
			if(shapeFinding(propertyWord))
                            property="shape";
			else if(colorFinding(propertyWord))
                            property="color";
			else if(patternFinding(propertyWord))
                            property="pattern";
                        else if(materialFinding(propertyWord))
                            property="material";
                        System.out.println("2. "+property+"(" + nsubj + "," + propertyWord + ")");
                    }
                    
                    /*semgrex = SemgrexPattern.compile(patterns[2]);
                    System.out.println("Pattern: "+semgrex.pattern()+"  ....");
                    matcher = semgrex.matcher(graph);
                    if(matcher.find()){
                          //  System.out.println("gfg");
                            String reln = matcher.getRelnString("objreln");
                            System.out.println(reln);
			String pobj = matcher.getNode("pobj").word();//send dobj to get the property of the word extracted here
			String nsubj = matcher.getNode("nsubj").word();
                        String propertyWord = matcher.getNode("dobj").word();
			String property="";
			if(shapeFinding(propertyWord))
                            property="shape";
			else if(colorFinding(propertyWord))
                            property="color";
			else if(patternFinding(propertyWord))
                            property="pattern";
                        else if(materialFinding(propertyWord))
                            property="material";
                        System.out.println("3. "+property+"(" + nsubj + "," + pobj + ")");
                    }
                    
                    semgrex = SemgrexPattern.compile(patterns[3]);
                    System.out.println("Pattern: "+semgrex.pattern()+"  ....");
                    matcher = semgrex.matcher(graph);
                    
                    if(matcher.find())
                    {
                       String pobj = matcher.getNode("pobj").word();//send dobj to get the property of the word extracted here
			String nsubj = matcher.getNode("nsubj").word();
                        String propertyWord = matcher.getNode("advmod").word();
			String property="";
			if(shapeFinding(propertyWord))
                            property="shape";
			else if(colorFinding(propertyWord))
                            property="color";
			else if(patternFinding(propertyWord))
                            property="pattern";
                        else if(materialFinding(propertyWord))
                            property="material";
                        System.out.println("4. "+property+"(" + nsubj + "," + pobj + ")");
                    }
                     semgrex = SemgrexPattern.compile(patterns[4]);
                    System.out.println("Pattern: "+semgrex.pattern()+"  ....");
                    matcher = semgrex.matcher(graph);
                    
                    if(matcher.find())
                    {
                       String pobj = matcher.getNode("pobj").word();//send dobj to get the property of the word extracted here
			String nsubj = matcher.getNode("nsubj").word();
                        String propertyWord = matcher.getNode("prep").word();
			String property="";
			if(shapeFinding(propertyWord))
                            property="shape";
			else if(colorFinding(propertyWord))
                            property="color";
			else if(patternFinding(propertyWord))
                            property="pattern";
                        else if(materialFinding(propertyWord))
                            property="material";
                        System.out.println("5. "+property+"(" + nsubj + "," + pobj + ")");
                    }
                     semgrex = SemgrexPattern.compile(patterns[5]);
                    System.out.println("Pattern: "+semgrex.pattern()+"  ....");
                    matcher = semgrex.matcher(graph);
                    
                    if(matcher.find())
                    {
                       String pobj = matcher.getNode("pobj2").word();//send dobj to get the property of the word extracted here
			String nsubj = matcher.getNode("nsubj").word();
                        String propertyWord = matcher.getNode("pobj").word();
			String property="";
			if(shapeFinding(propertyWord))
                            property="shape";
			else if(colorFinding(propertyWord))
                            property="color";
			else if(patternFinding(propertyWord))
                            property="pattern";
                        else if(materialFinding(propertyWord))
                            property="material";
                        System.out.println("6. "+property+"(" + nsubj + "," + pobj + ")");
                    }*/
                }
    }
    public void initializeScenesAndDescriptions(){
        JSONParser parser;
        parser = new JSONParser();
        try{
            JSONObject o = (JSONObject) parser.parse(new FileReader("src\\resources\\json\\descriptions.json"));
            Set<String> keys = (Set<String>) o.keySet();
            for(String key:keys){
                String descriptionId = key;
                JSONObject o1 = (JSONObject) o.get(key);
                JSONObject o2 = (JSONObject) o1.get("data");
                String description = (String)o2.get("description");
                JSONObject o3 = (JSONObject) o2.get("entry");
                String sceneIdForDescription = (String) o3.get("id");
                Description desc = new Description(descriptionId, description, sceneIdForDescription);
                descriptions.add(desc);
            }
            o = (JSONObject) parser.parse(new FileReader("src\\resources\\json\\scenes.json"));
            keys = (Set<String>) o.keySet();
            for(String key:keys){
                String sceneId = key;
                JSONObject o1 = (JSONObject) o.get(key);
                JSONObject o2 = (JSONObject) o1.get("data");
                JSONObject o3 = (JSONObject) o2.get("entry");
                String descriptionIdForScene = (String) o3.get("description");
                Scene scene = new Scene(sceneId, descriptionIdForScene);
                scenes.add(scene);
            }
            o = (JSONObject) parser.parse(new FileReader("src\\resources\\json\\seed_sentences.json"));
            keys = (Set<String>) o.keySet();
            for(String key:keys){
               String sentenceId = key;
               JSONObject o1 = (JSONObject) o.get(key);
               String sentence =(String) o1.get("sentence");
               String scene_type =(String) o1.get("scene_type");
               if(scene_type.equals("bedroom"))
               bedroom_sentences.add(sentence);
               if(scene_type.equals("living_room"))
               living_room_sentences.add(sentence);
               if(scene_type.equals("office"))
               office_sentences.add(sentence);  
            }
            o = (JSONObject) parser.parse(new FileReader("src\\resources\\json\\objectsSupport.json"));
            keys = (Set<String>) o.keySet();
            for(String key:keys){
               JSONObject o1 = (JSONObject) o.get(key);
               Set<String> objs= (Set<String>)o1.keySet();
               for(String k:objs)
               {
                   if(key.equals("living_room"))
                   {
                    String supportObj =(String) o1.get(k);
                    living_room_support.put(k,supportObj);
                   }
                   else  if(key.equals("bedroom"))
                   {
                    String supportObj =(String) o1.get(k);
                    bedroom_support.put(k,supportObj);
                   }
                   else  if(key.equals("office"))
                   {
                    String supportObj =(String) o1.get(k);
                    office_support.put(k,supportObj);
                   }
               }
               //System.out.println(living_room_support);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            JSONObject o = (JSONObject) parser.parse(new FileReader("src\\resources\\json\\relations.json"));
            JSONArray ar1 = (JSONArray)o.get("standard_relations");
            for (int i=0; i<ar1.size(); i++){ 
                standardRelations.add((String)ar1.get(i));
            } 
            //System.out.println(standardRelations);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void initRelationsMapping(){
        JSONParser parser;
        parser = new JSONParser();
        try{
            JSONObject o = (JSONObject) parser.parse(new FileReader("src\\resources\\json\\relations.json"));
            JSONObject o1 = (JSONObject)o.get("relations_mapping");
            JSONObject o2 = (JSONObject)o1.get(scene_type);
            Set<String> keys = (Set<String>) o2.keySet();
            for(String key:keys){
                String sentence_relation = key;
                String standard_relation = (String)o2.get(key);
                relationsMapping.put(sentence_relation, standard_relation);
            }
            //System.out.println(relationsMapping);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String coreferencePart(String text){
        //Adding words to replace in sentences
        Map<Integer, CorefChain> graph1 = document.get(CorefChainAnnotation.class);
        if(graph1.toString().equals("{}")){
            return text;
        }
        HashMap<Integer, ArrayList<String>> wordsHashMap = new HashMap<Integer, ArrayList<String>>();
        HashMap<Integer, ArrayList<String>> wordsReplaceHashMap = new HashMap<Integer, ArrayList<String>>();
        for(int i:graph1.keySet()){
            CorefMention representativeMention = graph1.get(i).getRepresentativeMention();
            String word = representativeMention.mentionSpan;
            List<CorefMention> corefMentions = graph1.get(i).getMentionsInTextualOrder();
            for(int j=0; j<corefMentions.size(); j++){
                int sentenceNumber = corefMentions.get(j).sentNum;
                String wordToReplace = corefMentions.get(j).mentionSpan;
                ArrayList<String> words1;
                ArrayList<String> replaceWords1;
                if(wordsHashMap.containsKey(sentenceNumber)){
                    words1 = wordsHashMap.get(sentenceNumber);
                    replaceWords1 = wordsReplaceHashMap.get(sentenceNumber);
                    wordsHashMap.remove(sentenceNumber);
                    wordsReplaceHashMap.remove(sentenceNumber);
                }
                else{
                    words1 = new ArrayList<>();
                    replaceWords1 = new ArrayList<>();
                }
                words1.add(word);
                replaceWords1.add(wordToReplace);
                wordsHashMap.put(sentenceNumber, words1);
                wordsReplaceHashMap.put(sentenceNumber, replaceWords1);
            }
        }
        //replacing with desired words
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        String source = text;
        iterator.setText(source);
        int start = iterator.first();
        int senNumber = 1;
        String finalText = "";
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String sentence = source.substring(start,end);
            ArrayList<String> wordsArrayList = wordsHashMap.get(senNumber);
            ArrayList<String> wordsReplaceArrayList = wordsReplaceHashMap.get(senNumber);
            for(int i=0; i<wordsArrayList.size(); i++){
                String word = wordsArrayList.get(i);
                String replaceWord = wordsReplaceArrayList.get(i);
                int index = sentence.indexOf(replaceWord);
                while (index >= 0) {
                    if(index == 0 || sentence.charAt(index-1)==' '){
                        sentence = sentence.substring(0, index) + word + sentence.substring(index+replaceWord.length());
                        //sentence = sentence.replace(replaceWord, word);
                    }
                    index = sentence.indexOf(replaceWord, index + 1);
                }
            }
            finalText += sentence;
            senNumber++;
        }
        return finalText;
        
    }
    public void initializeObjectsAndAttributes(List<CoreMap> sentences){
        /*POS Labels:
        
        CC Coordinating conjunction
        CD Cardinal number
        DT Determiner
        EX Existential there
        FW Foreign word
        IN Preposition or subordinating conjunction
        JJ Adjective
        JJR Adjective, comparative
        JJS Adjective, superlative
        LS List item marker
        MD Modal
        NN Noun, singular or mass
        NNS Noun, plural
        NNP Proper noun, singular
        NNPS Proper noun, plural
        PDT Predeterminer
        POS Possessive ending
        PRP Personal pronoun
        PRP$ Possessive pronoun
        RB Adverb
        RBR Adverb, comparative
        RBS Adverb, superlative
        RP Particle
        SYM Symbol
        TO to
        UH Interjection
        VB Verb, base form
        VBD Verb, past tense
        VBG Verb, gerund or present participle
        VBN Verb, past participle
        VBP Verb, non­3rd person singular present
        VBZ Verb, 3rd person singular present
        WDT Wh­determiner
        WP Wh­pronoun
        WP$ Possessive wh­pronoun
        WRB Wh­adverb*/
        
        objAttrPair.clear();
        for(CoreMap sentence: sentences) {
            // traversing the words in the current sentence
            // a CoreLabel is a CoreMap with additional token-specific methods
            int made = 0; //initially, word made not found
            ArrayList<String> tempObjs = new ArrayList<>();
            ArrayList<String> tempAttrs = new ArrayList<>();
            String prevWord = null;
            String prevAdjective = null;
            int objCountForAttr = 0; //maintains count of objects added for the latest attribute
            
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(NamedEntityTagAnnotation.class); 
                // perform lemmatization
                String lem = token.get(LemmaAnnotation.class);
                //System.out.println("Lemma: "+lem+"...pos: "+pos+"...word: "+word);
               
                if((containsElement(toBeObjects, lem) || containsElement(toBeObjectsPlural, lem) || pos.equals("NN") || pos.equals("NNS")) && !relationsMapping.containsKey(word)){
                    //mapping objects and attributes
                    if(!physicalObjFinding(lem) && !(containsElement(toBeObjects, lem) || containsElement(toBeObjectsPlural, lem))){ //attribute (not visualizable)
                        if(!tempAttrs.contains(lem)){
                            objCountForAttr = 0;
                            if(tempObjs.size() > 0){
                                for(int i=0; i<tempObjs.size(); i++){
                                    objAttrPair.put(tempObjs.get(i), lem);
                                    objCountForAttr++;
                                }
                            }
                            else{
                                tempAttrs.add(lem);
                            }
                            tempObjs.clear();
                        }
                    }
                    else if(prevWord != null){
                        objAttrPair.put(lem, prevWord);
                        tempAttrs.clear();
                        tempObjs.clear();
                        objCountForAttr = 0;
                    }
                    else if(prevAdjective != null){
                        for(int i=1; i<=objCountForAttr; i++){ //remove unnecessary additions in objAttrPair
                            String objKey = objectCategories.get(objectCategories.size()-i);
                            if(objAttrPair.containsKey(objKey) && objAttrPair.get(objKey).equals(prevAdjective)){
                                objAttrPair.remove(objKey);
                            }
                        }
                        objAttrPair.put(lem, prevAdjective);
                        tempAttrs.clear();
                        tempObjs.clear();
                        objCountForAttr = 0;
                    }
                    else if(made == 1){ //current word is attribute material (but will come as object)
                        for(int i=0; i<tempObjs.size(); i++){
                            objAttrPair.put(tempObjs.get(i), lem);
                        }
                        tempObjs.clear();
                        tempAttrs.clear();
                        objCountForAttr = 0;
                    }
                    else if(!tempObjs.contains(lem)){ //object
                        if(tempAttrs.size() > 0){
                            for(int i=0; i<tempAttrs.size(); i++){
                                objAttrPair.put(lem, tempAttrs.get(i));
                            }
                        }
                        else{
                            tempObjs.add(lem);
                        }
                        tempAttrs.clear();
                    }
                    //adding in the list of nouns, other nouns and objects
                    if(!nouns.contains(lem)){
                        nouns.add(lem);
                        if(!(containsElement(toBeObjects, lem) || containsElement(toBeObjectsPlural, lem)) && !physicalObjFinding(lem)){
                            otherNouns.add(lem);
                            if(!attributes.contains(lem)){
                                attributes.add(lem);
                            }
                        }
                        else{
                            if(made == 1){
                                made = 0;
                                if(!attributes.contains(lem)){
                                    attributes.add(lem);
                                }
                            }
                            else{
                                objectCategories.add(lem);
                            }
                            if(prevWord != null){
                                objectCategories.remove(prevWord);
                                if(!attributes.contains(lem)){
                                    attributes.add(lem);
                                }
                            }
                        }
                    }
                }
                else if((!(containsElement(toBeObjects, lem) || containsElement(toBeObjectsPlural, lem)) && pos.equals("JJ")) && !relationsMapping.containsKey(word)){
                    //mapping objects and attributes
                    if(!tempAttrs.contains(lem)){
                        objCountForAttr = 0;
                        if(tempObjs.size() > 0){
                            for(int i=0; i<tempObjs.size(); i++){
                                objAttrPair.put(tempObjs.get(i), lem);
                                objCountForAttr++;
                            }
                        }
                        else{
                            tempAttrs.add(lem);
                        }
                        tempObjs.clear();
                    }
                    
                    //adding in the list of adjectives
                    if(!adjectives.contains(lem)){
                        adjectives.add(lem);
                        if(!attributes.contains(lem)){
                            attributes.add(lem);
                        }
                    }
                }
                else{
                    //System.out.println("Other:"+lem+"    :   "+pos);
                    if(word.equalsIgnoreCase("made")){
                        made=1;
                    }
                }
                if(pos.equals("NN") || pos.equals("NNS")){
                    prevWord = lem;
                }
                else{
                    prevWord = null;
                }
                if(pos.equals("JJ")){
                    prevAdjective = lem;
                }
                else{
                    prevAdjective = null;
                }
                /*System.out.print("word: "+word+"...");
                System.out.print("pos: "+pos+"....");
                System.out.print("ne: "+ne+"....");
                System.out.print("lemma: "+lem+"...");
                System.out.println();*/
            }
        }
        provideAttributeTypes(objAttrPair);
        //System.out.println("Pairs: "+objAttrPair);
    }
    public void initializeRelations(List<CoreMap> sentences){
        
        for(CoreMap sentence: sentences) {
            ArrayList<String> tempObjs = new ArrayList<>();
            ArrayList<String> tempAttrs = new ArrayList<>();
            String prevWord = null;
            String obj1 = null;
            String obj2 = null;
            String relation = null;
            
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
                // this is the text of the token
                String word = token.get(TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String ne = token.get(NamedEntityTagAnnotation.class); 
                // perform lemmatization
                String lem = token.get(LemmaAnnotation.class);
                if(relationsMapping.containsKey(word)){
                    relation = relationsMapping.get(word);
                }
                else if(objectCategories.contains(lem)){
                    if(obj1 == null){
                        obj1 = lem;
                    }
                    else{ //two objects available
                        if(relation == null){ //no relation b/w these two objects
                            obj1 = lem;
                        }
                        else{ //obj1 x relation x obj2 found
                            obj2 = lem;
                            ArrayList<String[]> relObjects;
                            if(relations.containsKey(relation)){
                                relObjects = relations.get(relation);
                            }
                            else{
                                relObjects = new ArrayList<>();
                            }
                            relObjects.add(new String[]{obj1,obj2});
                            relations.put(relation, relObjects);
                        }
                    }
                }
            }
        }
    }
    public void provideAttributeTypes(HashMap<String, String> objAttrPair){
        for(String obj:objAttrPair.keySet()){
            String attr = objAttrPair.get(obj);
            if(colorFinding(attr)){
                objAttrWithType.put(obj, new String[]{attr, "color"});
            }
            else if(shapeFinding(attr)){
                objAttrWithType.put(obj, new String[]{attr, "shape"});
            }
            else if(materialFinding(attr)){
                objAttrWithType.put(obj, new String[]{attr, "material"});
            }
            else{
                objAttrWithType.put(obj, new String[]{attr, "pattern"});
            }
        }
    }
    public boolean physicalObjFinding(String t)
    {
        return net.isPhysicalObject(t) && !net.isLocation(t);
    }
    public boolean colorFinding(String t)
    {
       return net.isColor(t);
    }
    public boolean shapeFinding(String t)
    {
       return net.isShape(t);
    }
    public boolean materialFinding(String t)
    {
       return net.isMaterial(t);
    }

    public boolean patternFinding(String t)
    {
       return net.isPattern(t);
    }
    public void initializeSceneType(String text){
        String st="";
        String scene_type="";
        for(int i=0;i<text.length();i++)
        {
            char ch=text.charAt(i);
            if(ch == ' ')
            {
                if(st.equalsIgnoreCase("bedroom"))
                    scene_type="bedroom";
                else if(st.equalsIgnoreCase("office"))
                    scene_type="office";
                else if(st.equalsIgnoreCase("living-room"))
                    scene_type="living_room";
                st="";
            }
            else
                st=st+ch;
                
        }
        //scene type inference from probability formula
         if(scene_type.equals(""))//infer scene type
        {
            double x=0.0, y=0.0, z=0.0;
           
            for(String obj:objectCategories)//for each object
            {
                int count1=0,count2=0,count3=0;
                for(String sentence:living_room_sentences)
                {
                    if(sentence.contains(obj))
                        count1++;
                }
                for(String sentence:bedroom_sentences)
                {
                    if(sentence.contains(obj))
                        count2++;
                }
               // System.out.println(obj+","+count2+","+((double)count2/bedroom_sentences.size()));
                for(String sentence:office_sentences)
                {
                    if(sentence.contains(obj))
                        count3++;
                }
               
                x=x+((double)count1/living_room_sentences.size());
               
                y=y+((double)count2/bedroom_sentences.size());
               
                z=z+((double)count3/office_sentences.size());
            }
           // System.out.println(x+","+y+","+z);
            x=x*(living_room_sentences.size()/60.0);
            y=y*(bedroom_sentences.size()/60.0);
            z=z*(office_sentences.size()/60.0);
            if(x>y && x>z)
            {
                scene_type="living_room";
            }
            else if(y>z && y> x)
            {
                scene_type="bedroom";
            }
            else
            {
                scene_type="office";
            }
         }
       
    }
    public void sceneParsing(String text){
        text = text.toLowerCase();
        try{
            document = new Annotation(text);
            pipeline.annotate(document);

            text = coreferencePart(text);
            // create an empty Annotation just with the given text
            document = new Annotation(text);
            pipeline.annotate(document);
        
            List<CoreMap> sentences = document.get(SentencesAnnotation.class);
            initRelationsMapping();
            initializeObjectsAndAttributes(sentences);
            initializeSceneType(text);
            initializeRelations(sentences);
            //semgrexPart(sentences);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sceneInference(String text)
    {
        if(scene_type.equals("living_room"))
        {
            if(!objectCategories.contains(inferObjsLivingRoom[0]))
                objectCategories.add(inferObjsLivingRoom[0]);
            if(!objectCategories.contains(inferObjsLivingRoom[1]))
                objectCategories.add(inferObjsLivingRoom[1]);
        }
        else if(scene_type.equals("bedroom"))
        {
            if(!objectCategories.contains(inferObjsBedRoom[0]))
                objectCategories.add(inferObjsBedRoom[0]);
            if(!objectCategories.contains(inferObjsBedRoom[1]))
                objectCategories.add(inferObjsBedRoom[1]);
        }
        else if(scene_type.equals("office"))
        {
            if(!objectCategories.contains(inferObjsOfficeRoom[0]))
                objectCategories.add(inferObjsOfficeRoom[0]);
            if(!objectCategories.contains(inferObjsOfficeRoom[1]))
                objectCategories.add(inferObjsOfficeRoom[1]);
        }
        
        //Find supported objects and arrange in hierarchy
        String supportObject = "";
        ArrayList<String> moreObjects = new ArrayList<>();
        ArrayList<String> objects1 = new ArrayList<>();
        ArrayList<String> objects2 = new ArrayList<>();
        ArrayList<String> relationsList = new ArrayList<>();
        for(String rel:relations.keySet()){
            ArrayList<String[]> relObjects = relations.get(rel);
            for(String[] objects:relObjects){
                String obj1 = objects[0];
                String obj2 = objects[1];
                objects1.add(obj1);
                objects2.add(obj2);
                relationsList.add(rel);
            }
        }
        for(String s:objectCategories)//find support for each object
        {
            if((!s.equals(("room")))&&scene_type.equals("living_room"))
            {
                if(living_room_support.containsKey(s))
                    supportObjects.put(s, living_room_support.get(s));
                supportObject = living_room_support.get(s);
                
            }
            else if((!s.equals(("room")))&&scene_type.equals("bedroom"))
            {
                if(bedroom_support.containsKey(s))
                    supportObjects.put(s, bedroom_support.get(s));
                supportObject = bedroom_support.get(s);
            }
            else if((!s.equals(("room")))&&scene_type.equals("office"))
            {
                if(office_support.containsKey(s))
                    supportObjects.put(s, office_support.get(s));
                supportObject = office_support.get(s);
            }
            if(!s.equals("room")){
                int i=0;
                for(String object1:objects1){
                    if(object1.equals(s)){
                        String object2 = objects2.get(i);
                        String relation = relationsList.get(i);
                        //relations.get(relation)[0] = supportObject;
                        if(relation.equals("supported_by") && !supportObject.equals(object2)){
                            supportObjects.put(supportObject, object2); //object1 was supported_by object2.. now, support object is supported by object2
                        }
                    }
                    i++;
                }
                i=0;
                for(String object2:objects2){
                    if(object2.equals(s)){
                        String object1 = objects1.get(i);
                        String relation = relationsList.get(i);
                        //relations.get(relation)[1] = supportObject;
                        if(relation.equals("supports") && !supportObject.equals(object1)){
                            supportObjects.put(supportObject, object1); //object1 supported object2,i.e., object2 was supported_by object1.... now, supportObject is supported by object1
                        }
                    }
                    i++;
                }
                if(!supportObject.equals("") && !objectCategories.contains(supportObject)){
                    moreObjects.add(supportObject);
                }
            }
        }
        for(String objc:moreObjects){
            objectCategories.add(objc);
        }
        System.out.println("inside "+objectCategories);
        System.out.println("inside "+supportObjects);
    }
    /**
     * @param args the command line arguments
     */
    public void displayParsing(){
        System.out.println("nouns: "+nouns);
        System.out.println("adjectives: "+adjectives);
        System.out.println("other nouns: "+otherNouns);
        System.out.println("objectCategories: "+objectCategories);
        System.out.println("Attributes: "+attributes);
        System.out.print("Relations: ");
        displayString = displayString+"Objects: "+objectCategories+"\r\nAttributes: "+attributes+"\r\nRelations: ";
        /*for(String rel:relations.keySet()){
            String obj1 = relations.get(rel)[0];
            String obj2 = relations.get(rel)[1];
            System.out.print(rel+"("+obj1+", "+obj2+")"+"........");
            displayString += rel+"("+obj1+", "+obj2+")"+"........";
        }*/
        for(String rel:relations.keySet()){
            ArrayList<String[]> relObjects = relations.get(rel);
            for(String[] objects:relObjects){
                String obj1 = objects[0];
                String obj2 = objects[1];
                System.out.print(rel+"("+obj1+", "+obj2+")"+"........");
                displayString += rel+"("+obj1+", "+obj2+")"+"........";
            }
        }
        System.out.print("\r\nObj-Attributes: ");
        displayString += "\r\nObject-Attribute Pairs: ";
        for(String obj:objAttrWithType.keySet()){
            String attr = objAttrWithType.get(obj)[0];
            String type = objAttrWithType.get(obj)[1];
            System.out.print(type+"("+obj+", "+attr+")"+"........");
            displayString += type+"("+obj+", "+attr+")"+"........";
        }
        System.out.println("\n");
        displayString += "\r\n\r\n";
        jTextPane1.setText(displayString);
    }
    public void createSceneGraph(){
        for(String obj:objAttrWithType.keySet()){
            String attr = objAttrWithType.get(obj)[0];
            String type = objAttrWithType.get(obj)[1];
            Node node = new Node(obj, attr, type);
            nodes.put(obj, node);
            if(obj.equals("room")){
                root = node;
            }
        }
        for(String obj:objectCategories){
            Node node;
            if(!nodes.containsKey(obj)){
                node = new Node(obj, null, null);
                nodes.put(obj, node);
                if(obj.equals("room")){
                    root = node;
                }
            }
        }
        /*for(String rel:relations.keySet()){
            String obj1 = relations.get(rel)[0];
            String obj2 = relations.get(rel)[1];
            Node node1 = nodes.get(obj1);
            Node node2 = nodes.get(obj2);
            if(!rel.equals("supports") && !rel.equals("supported_by") && !node1.next.contains(node2)){
                node1.next.add(node2);
                node1.connectedAs.add(rel);
            }
        }*/
        for(String rel:relations.keySet()){
            ArrayList<String[]> relObjects = relations.get(rel);
            for(String[] objects:relObjects){
                String obj1 = objects[0];
                String obj2 = objects[1];
                Node node1 = nodes.get(obj1);
                Node node2 = nodes.get(obj2);
                if(!rel.equals("supports") && !rel.equals("supported_by") && !node1.next.contains(node2)){
                    node1.next.add(node2);
                    node1.connectedAs.add(rel);
                }
            }
        }
        for(String obj2:supportObjects.keySet()){
            String obj1 = supportObjects.get(obj2);
            Node node1 = nodes.get(obj1);
            Node node2 = nodes.get(obj2);
            if(!node1.next.contains(node2)){
                node1.next.add(node2);
                node1.connectedAs.add("supports");
            }
        }
        giveLevelsToNodes(root, 0);
    }
    public void giveLevelsToNodes(Node root, int currLevel){
        root.level = currLevel;
        ArrayList<Node> nodesOnThisLevel;
        if(levelNodes.containsKey(currLevel)){
            nodesOnThisLevel = levelNodes.get(currLevel);
        }
        else{
            nodesOnThisLevel = new ArrayList<>();
        }
        nodesOnThisLevel.add(root);
        levelNodes.put(currLevel, nodesOnThisLevel);
        
        int i=0;
        for(Node node:root.next){
            String connectedAs = root.connectedAs.get(i++);
            if(connectedAs.equals("supports")){
                if(!levelNodes.containsKey(currLevel + 1)){
                    giveLevelsToNodes(node, currLevel+1);
                }
                else{
                    ArrayList<Node> nodesOnNextLevel = levelNodes.get(currLevel+1);
                    if(!nodesOnNextLevel.contains(node)){
                        giveLevelsToNodes(node, currLevel+1);
                    }
                }
            }
            /*else if(connectedAs.equals("supported_by")){
                if(!levelNodes.containsKey(currLevel - 1)){
                    giveLevelsToNodes(node, currLevel-1);
                }
                else{
                    ArrayList<Node> nodesOnPreviousLevel = levelNodes.get(currLevel-1);
                    if(!nodesOnPreviousLevel.contains(node)){
                        giveLevelsToNodes(node, currLevel-1);
                    }
                }
            }*/
        }
    }
    public void displaySceneGraph(){
        System.out.println("\nScene Graph:....");
        for(String obj:nodes.keySet()){
            Node node = nodes.get(obj);
            System.out.print(node.name+"...."+node.attribute+"...");
            int i=0;
            for(String conn:node.connectedAs){
                Node node1 = node.next.get(i++);
                System.out.print(node1.name+"="+conn+"....");
            }
            System.out.println();
        }
        for(int level:levelNodes.keySet()){
            ArrayList<Node> nodes = levelNodes.get(level);
            System.out.print("Level "+level+" = ");
            for(Node node:nodes){
                System.out.print(node.name+".....");
            }
            System.out.println();
        }
    }
    public void displayInference(){
        System.out.println("Scene Type= "+scene_type+"\n\nAfter Inference...\nObjects: ");
        displayString += "Scene Type= "+scene_type+"\n\nAfter Inference...\nObjects: ";
        for(String s:objectCategories)//all objects after inference
        {
            System.out.print(s+"  ");
            displayString += s+" ";
        }
        System.out.println();
        System.out.println("Support Objects:  "+supportObjects);
        displayString += "\nSupport Objects: "+supportObjects;
        jTextPane1.setText(displayString);
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    private boolean containsElement(String[] s1, String s){
        for(int i=0; i<s1.length; i++){
            if(s1[i].equals(s)){
                return true;
            }
        }
        return false;
    }
    class Node{
        String name;
        String attribute;
        String attributeType;
        int level=0;
        ArrayList<Node> next;
        ArrayList<String> connectedAs;
        public Node(String name, String attribute, String attributeType){
            this.name = name;
            this.attribute = attribute;
            this.attributeType = attributeType;
            next = new ArrayList<>();
            connectedAs = new ArrayList<>();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
