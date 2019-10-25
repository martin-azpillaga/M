/*
 * generated by Xtext
 */
package m.ui.internal;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Collections;
import java.util.Map;
import m.BlocksRuntimeModule;
import m.CSRuntimeModule;
import m.JsonRuntimeModule;
import m.MRuntimeModule;
import m.TestuaRuntimeModule;
import m.TextRuntimeModule;
import m.YAMLRuntimeModule;
import m.ui.BlocksUiModule;
import m.ui.CSUiModule;
import m.ui.JsonUiModule;
import m.ui.MUiModule;
import m.ui.TestuaUiModule;
import m.ui.TextUiModule;
import m.ui.YAMLUiModule;
import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class MActivator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "m.ui";
	public static final String M_M = "m.M";
	public static final String M_TEXT = "m.Text";
	public static final String M_TESTUA = "m.Testua";
	public static final String M_BLOCKS = "m.Blocks";
	public static final String M_YAML = "m.YAML";
	public static final String M_CS = "m.CS";
	public static final String M_JSON = "m.Json";
	
	private static final Logger logger = Logger.getLogger(MActivator.class);
	
	private static MActivator INSTANCE;
	
	private Map<String, Injector> injectors = Collections.synchronizedMap(Maps.<String, Injector> newHashMapWithExpectedSize(1));
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		injectors.clear();
		INSTANCE = null;
		super.stop(context);
	}
	
	public static MActivator getInstance() {
		return INSTANCE;
	}
	
	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}
	
	protected Injector createInjector(String language) {
		try {
			com.google.inject.Module runtimeModule = getRuntimeModule(language);
			com.google.inject.Module sharedStateModule = getSharedStateModule();
			com.google.inject.Module uiModule = getUiModule(language);
			com.google.inject.Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, uiModule);
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			logger.error("Failed to create injector for " + language);
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for " + language, e);
		}
	}
	
	protected com.google.inject.Module getRuntimeModule(String grammar) {
		if (M_M.equals(grammar)) {
			return new MRuntimeModule();
		}
		if (M_TEXT.equals(grammar)) {
			return new TextRuntimeModule();
		}
		if (M_TESTUA.equals(grammar)) {
			return new TestuaRuntimeModule();
		}
		if (M_BLOCKS.equals(grammar)) {
			return new BlocksRuntimeModule();
		}
		if (M_YAML.equals(grammar)) {
			return new YAMLRuntimeModule();
		}
		if (M_CS.equals(grammar)) {
			return new CSRuntimeModule();
		}
		if (M_JSON.equals(grammar)) {
			return new JsonRuntimeModule();
		}
		throw new IllegalArgumentException(grammar);
	}
	
	protected com.google.inject.Module getUiModule(String grammar) {
		if (M_M.equals(grammar)) {
			return new MUiModule(this);
		}
		if (M_TEXT.equals(grammar)) {
			return new TextUiModule(this);
		}
		if (M_TESTUA.equals(grammar)) {
			return new TestuaUiModule(this);
		}
		if (M_BLOCKS.equals(grammar)) {
			return new BlocksUiModule(this);
		}
		if (M_YAML.equals(grammar)) {
			return new YAMLUiModule(this);
		}
		if (M_CS.equals(grammar)) {
			return new CSUiModule(this);
		}
		if (M_JSON.equals(grammar)) {
			return new JsonUiModule(this);
		}
		throw new IllegalArgumentException(grammar);
	}
	
	protected com.google.inject.Module getSharedStateModule() {
		return new SharedStateModule();
	}
	
	
}
