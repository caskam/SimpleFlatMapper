package org.simpleflatmapper.reflect.impl;

import org.simpleflatmapper.reflect.BiInstantiator;
import org.simpleflatmapper.reflect.Instantiator;
import org.simpleflatmapper.reflect.MethodBiFunctionPair;
import org.simpleflatmapper.util.ErrorHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class BuilderBiInstantiator<S1, S2, T> implements BiInstantiator<S1, S2, T> {

	private final Instantiator<Void, ?> builderInstantiator;
	private final MethodBiFunctionPair<S1, S2>[] chainedArguments;
	private final MethodBiFunctionPair<S1, S2>[] unchainedArguments;
	private final Method buildMethod;

	public BuilderBiInstantiator(
			Instantiator<Void, ?> builderInstantiator,
			MethodBiFunctionPair<S1, S2>[] chainedArguments,
			MethodBiFunctionPair<S1, S2>[] unchainedArguments,
			Method buildMethod) {
		this.builderInstantiator = builderInstantiator;
		this.chainedArguments = chainedArguments;
		this.unchainedArguments = unchainedArguments;
		this.buildMethod = buildMethod;
	}


	@Override
	@SuppressWarnings("unchecked")
	public T newInstance(S1 s1, S2 s2) throws Exception {
		try {
			Object builder = builderInstantiator.newInstance(null);
			for (MethodBiFunctionPair<S1, S2> argument : chainedArguments) {
				builder = argument.getMethod().invoke(builder, argument.getFunction().apply(s1, s2));
			}
			for (MethodBiFunctionPair<S1, S2> argument : unchainedArguments) {
				argument.getMethod().invoke(builder, argument.getFunction().apply(s1, s2));
			}
			return (T) buildMethod.invoke(builder);
		} catch (InvocationTargetException e) {
			return ErrorHelper.rethrow(e.getCause());
		}
	}
}
