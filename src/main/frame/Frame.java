package main.frame;

import main.operation.Operation;
import main.symbol.Symbol;

import java.util.*;

public class Frame {
    private Map<String, Symbol> symbols;
    private Frame parent;

    public void AddSymbol(String name, Operation operation, Object value) {

        if (name == null) {
            return;
            // throw error.
        }
        if ((operation == null && value == null)) {
            return;
            // throw error.
        }
        Symbol newSymbol;

        try {
            newSymbol = new Symbol(name, operation, value);
        } catch (Exception e) {
            // throw error.
            return;
        }
        // System.out.println("Putting Symbol: " + name + " to " + symbols);
        symbols.put(name, newSymbol);
    }

    public void RemoveSymbol(String name){
        if (name == null){
            return;
            // throw error.
        }
        try {
            symbols.remove(name);
        } catch (Exception e){
            // throw error.
        }
    }

    public Symbol GetSymbolByName(String name) throws Exception {
        Frame p = this.parent;
        Symbol symbol;
        //System.out.println("GetSymbol:" + name);
        symbol = symbols.get(name);
        if(symbol != null) return symbol;
        if(p != null) {
            symbol = p.GetSymbolByName(name);
        }
        if(symbol == null) throw new Exception("Symbol doesn't exist!");
        return symbol;
    }

    public List<Symbol> GetAllSymbols(){
        List<Symbol> allSymbols = new ArrayList<>();
        for(Map.Entry<String, Symbol> entry : symbols.entrySet()){
            Symbol symbol = entry.getValue();
            allSymbols.add(symbol);
        }
        return allSymbols;
    }

    public void ClearAllSymbols(){
        if(!symbols.isEmpty()){
            symbols.clear();
        }
    }

    public Boolean ContainsName(String name){
        if(symbols.containsKey(name)) return true;
        else return false;
    }

    public Frame(Frame parent) {
        this.symbols = new HashMap<>();
        this.parent = parent;
    }
}
