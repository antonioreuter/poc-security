package poc.security.audit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 310280812 on 3/29/2017.
 */
@Slf4j
@Component
public class AnnotationExpressionEvaluator {

    private static final String REGEX_EXP = "#\\{(.*)\\}";
    private static final Pattern pattern = Pattern.compile(REGEX_EXP);

    public Object evaluate(String exp, Object[] arguments, String[] params) {
        Object result = null;
        Matcher matcher = pattern.matcher(exp);
        if (matcher.matches()) {
            result = processExpression(matcher.group(1), arguments, params);
        } else {
            result = exp;
        }

        return result;
    }

    private Object processExpression(String exp, Object[] arguments, String[] params) {
        if (arguments == null || arguments.length == 0) {
            return null;
        }

        String[] tokens = exp.split("\\.");
        if (tokens.length == 0)
            throw new IllegalArgumentException("You need to inform an expression.");

        String target = tokens[0];
        Object contextScope = getContext(target, arguments, params);
        Object result = null;

        if (tokens.length == 1) {
            result = contextScope;
        }else {
            ExpressionParser parser = new SpelExpressionParser();
            StandardEvaluationContext context = new StandardEvaluationContext(contextScope);
            result = parser.parseExpression(getExp(tokens)).getValue(context);
        }
        return  result;
    }

    private String getExp(String[] tokens) {
        return  (tokens.length == 1) ? tokens[0] : tokens[1];
    }

    private Object getContext(String target, Object[] arguments, String[] params) {
        Object result = null;

        for (int i = 0; i < params.length; i++) {
            if (params[i].equals(target)) {
                result = arguments[i];
                break;
            }
        }

        return result;
    }
}
