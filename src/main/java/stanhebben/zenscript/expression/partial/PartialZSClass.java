package stanhebben.zenscript.expression.partial;

import stanhebben.zenscript.compiler.*;
import stanhebben.zenscript.expression.*;
import stanhebben.zenscript.symbols.IZenSymbol;
import stanhebben.zenscript.type.*;
import stanhebben.zenscript.util.ZenPosition;

public class PartialZSClass implements IPartialExpression {
    
    private final ZenTypeFrigginClass type;
    
    public PartialZSClass(ZenTypeFrigginClass type) {
        
        this.type = type;
    }
    
    @Override
    public Expression eval(IEnvironmentGlobal environment) {
        return new ExpressionNull(type.parsedFrigginClass.position) {
            @Override
            public void compile(boolean result, IEnvironmentMethod environment) {}
        };
    }
    
    @Override
    public Expression assign(ZenPosition position, IEnvironmentGlobal environment, Expression other) {
        throw new UnsupportedOperationException("Cannot assogn to a class");
    }
    
    @Override
    public IPartialExpression getMember(ZenPosition position, IEnvironmentGlobal environment, String name) {
        return type.getMember(position, environment, this, name);
    }
    
    @Override
    public Expression call(ZenPosition position, IEnvironmentMethod environment, Expression... values) {
        return type.call(position, environment, this.eval(environment), values);
    }
    
    @Override
    public ZenType[] predictCallTypes(int numArguments) {
        return type.predictCallTypes(numArguments);
    }
    
    @Override
    public IZenSymbol toSymbol() {
        return position -> this;
    }
    
    @Override
    public ZenType getType() {
        return type;
    }
    
    @Override
    public ZenType toType(IEnvironmentGlobal environment) {
        return type;
    }
}
