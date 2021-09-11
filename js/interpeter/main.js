function apply(procedure, args) {
    if (isPrimitiveProcedure(procedure)) {
        return applyPrimitiveProcedure(procedure, args);
    } else if (isCompoundProcedure(procedure)) {
        return evalSequence(
            procedureBody(procedure),
            extendEnvironment(
                procedureParams(procedure),
                args,
                procedureEnvironment(procedure)
            )
        )
    } else {
        throw new Error("Неизвестный тип процедуры: " + procedure)
    }
}

const emptyList = () => {
    return {
        car: () => null,
        cdr: () => emptyList()
    }
}

function list(car, cdr) {
    return {
        car: car,
        cdr: cdr
    }
}

function listOfValues(exps, env) {
    if (hasNotOperands(exps)) {
        return emptyList();
    } else {
        return list(
            eval(firstOperand(exps)),
            listOfValues(restOperands(exps), env))
    }
}

function evalIf(exp, env) {
    let evaluatedPredicate = eval(ifPredicate(exp), env);
    if (isTrue(evaluatedPredicate)) {
        return eval(ifConsequent(exp), env);
    } else {
        return eval(ifAlternative(exp), env);
    }
}

function evalSequence(exps, env) {
    if (isLastExp(exps)) {
        return eval(firstExp(exps), env);
    } else {
        eval(firstExp(exps), env);
        return evalSequence(restExps(exps), env);
    }
}

function evalAssignment(exp, env) {
    setVariableValue(assignmentVariable(exp), eval(assignmentValue(exp), env), env);
    return true;
}

function evalDefinition(exp, env) {
    defineVariable(definitionVariable(exp), eval(definitionValue(exp), env), env);
    return true;
}

function isSelfEvaluting(exp) {
    if (isNumber(exp)) {
        return true;
    } else if (isString(exp)) {
        return true;
    } else {
        return false;
    }
}

function isVariable(exp) {
    return isSymbol(exp);
}

function isQuoted(exp) {
    return isTaggedList(exp, "quote")
}

function isAssignment(exp) {
    return isTaggedList(exp, "set!");
}

function assignmentVariable(exp) {
    return exp.cdr().car();
}

function assignmentValue(exp) {
    return exp.cdr().cdr().car();
}


function isDeifinition(exp) {
    return isTaggedList(exp, "define")
}

function isTaggedList(exp, tag) {
    if (exp.car !== undefined) {
        return exp.car() === tag;
    }
    return false;
}

function eval(exp, env) {
    if (isSelfEvaluting(exp)) {
        return exp;
    } else if (isVariable(exp)) {
        return lookupVarValue(exp, env);
    } else if (isQuoted(exp)) {
        return textOfQuotation(exp);
    } else if (isAssignment(exp)) {
        return evalAssignment(exp, env);
    } else if (isDefinition(exp)) {
        return evalDefinition(exp, env);
    } else if (isIf(exp)) {
        return evalIf(exp, env);
    } else if (isLambda(exp)) {
        return makeProcedure(lambdaParams(exp), lambdaBody(exp), env);
    } else if (isBegin(exp)) {
        return evalSequence(beginActions(exp), env);
    } else if (isCond(exp)) {
        return eval(condIf(exp), env);
    } else if (isApplication(exp)) {
        return apply(eval(operator(exp), env), listOfValues(operands(exp), env));
    } else {
        throw new Error("Неизвестный тип выражения: " + exp)
    }
}