package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final CcLibraryAccessors laccForCcLibraryAccessors = new CcLibraryAccessors(owner);
    private final ComLibraryAccessors laccForComLibraryAccessors = new ComLibraryAccessors(owner);
    private final IoLibraryAccessors laccForIoLibraryAccessors = new IoLibraryAccessors(owner);
    private final NetLibraryAccessors laccForNetLibraryAccessors = new NetLibraryAccessors(owner);
    private final OrgLibraryAccessors laccForOrgLibraryAccessors = new OrgLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Group of libraries at <b>cc</b>
     */
    public CcLibraryAccessors getCc() {
        return laccForCcLibraryAccessors;
    }

    /**
     * Group of libraries at <b>com</b>
     */
    public ComLibraryAccessors getCom() {
        return laccForComLibraryAccessors;
    }

    /**
     * Group of libraries at <b>io</b>
     */
    public IoLibraryAccessors getIo() {
        return laccForIoLibraryAccessors;
    }

    /**
     * Group of libraries at <b>net</b>
     */
    public NetLibraryAccessors getNet() {
        return laccForNetLibraryAccessors;
    }

    /**
     * Group of libraries at <b>org</b>
     */
    public OrgLibraryAccessors getOrg() {
        return laccForOrgLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class CcLibraryAccessors extends SubDependencyFactory {
        private final CcCarmLibraryAccessors laccForCcCarmLibraryAccessors = new CcCarmLibraryAccessors(owner);

        public CcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>cc.carm</b>
         */
        public CcCarmLibraryAccessors getCarm() {
            return laccForCcCarmLibraryAccessors;
        }

    }

    public static class CcCarmLibraryAccessors extends SubDependencyFactory {
        private final CcCarmLibLibraryAccessors laccForCcCarmLibLibraryAccessors = new CcCarmLibLibraryAccessors(owner);

        public CcCarmLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>cc.carm.lib</b>
         */
        public CcCarmLibLibraryAccessors getLib() {
            return laccForCcCarmLibLibraryAccessors;
        }

    }

    public static class CcCarmLibLibraryAccessors extends SubDependencyFactory {
        private final CcCarmLibEasysqlLibraryAccessors laccForCcCarmLibEasysqlLibraryAccessors = new CcCarmLibEasysqlLibraryAccessors(owner);

        public CcCarmLibLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>cc.carm.lib.easysql</b>
         */
        public CcCarmLibEasysqlLibraryAccessors getEasysql() {
            return laccForCcCarmLibEasysqlLibraryAccessors;
        }

    }

    public static class CcCarmLibEasysqlLibraryAccessors extends SubDependencyFactory {

        public CcCarmLibEasysqlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>cc.carm.lib:easysql-api</b> coordinates and
         * with version reference <b>cc.carm.lib.easysql.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("cc.carm.lib.easysql.api");
        }

    }

    public static class ComLibraryAccessors extends SubDependencyFactory {
        private final ComGhostchuLibraryAccessors laccForComGhostchuLibraryAccessors = new ComGhostchuLibraryAccessors(owner);
        private final ComGithubLibraryAccessors laccForComGithubLibraryAccessors = new ComGithubLibraryAccessors(owner);
        private final ComGoogleLibraryAccessors laccForComGoogleLibraryAccessors = new ComGoogleLibraryAccessors(owner);
        private final ComOlziedevLibraryAccessors laccForComOlziedevLibraryAccessors = new ComOlziedevLibraryAccessors(owner);
        private final ComSk89qLibraryAccessors laccForComSk89qLibraryAccessors = new ComSk89qLibraryAccessors(owner);

        public ComLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.ghostchu</b>
         */
        public ComGhostchuLibraryAccessors getGhostchu() {
            return laccForComGhostchuLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.github</b>
         */
        public ComGithubLibraryAccessors getGithub() {
            return laccForComGithubLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.google</b>
         */
        public ComGoogleLibraryAccessors getGoogle() {
            return laccForComGoogleLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.olziedev</b>
         */
        public ComOlziedevLibraryAccessors getOlziedev() {
            return laccForComOlziedevLibraryAccessors;
        }

        /**
         * Group of libraries at <b>com.sk89q</b>
         */
        public ComSk89qLibraryAccessors getSk89q() {
            return laccForComSk89qLibraryAccessors;
        }

    }

    public static class ComGhostchuLibraryAccessors extends SubDependencyFactory {
        private final ComGhostchuQuickshopLibraryAccessors laccForComGhostchuQuickshopLibraryAccessors = new ComGhostchuQuickshopLibraryAccessors(owner);

        public ComGhostchuLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.ghostchu.quickshop</b>
         */
        public ComGhostchuQuickshopLibraryAccessors getQuickshop() {
            return laccForComGhostchuQuickshopLibraryAccessors;
        }

    }

    public static class ComGhostchuQuickshopLibraryAccessors extends SubDependencyFactory {

        public ComGhostchuQuickshopLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>com.ghostchu:quickshop-api</b> coordinates and
         * with version reference <b>com.ghostchu.quickshop.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("com.ghostchu.quickshop.api");
        }

        /**
         * Dependency provider for <b>bukkit</b> with <b>com.ghostchu:quickshop-bukkit</b> coordinates and
         * with version reference <b>com.ghostchu.quickshop.bukkit</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBukkit() {
            return create("com.ghostchu.quickshop.bukkit");
        }

    }

    public static class ComGithubLibraryAccessors extends SubDependencyFactory {
        private final ComGithubCortexLibraryAccessors laccForComGithubCortexLibraryAccessors = new ComGithubCortexLibraryAccessors(owner);

        public ComGithubLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.github.cortex</b>
         */
        public ComGithubCortexLibraryAccessors getCortex() {
            return laccForComGithubCortexLibraryAccessors;
        }

    }

    public static class ComGithubCortexLibraryAccessors extends SubDependencyFactory {
        private final ComGithubCortexMcLibraryAccessors laccForComGithubCortexMcLibraryAccessors = new ComGithubCortexMcLibraryAccessors(owner);

        public ComGithubCortexLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.github.cortex.mc</b>
         */
        public ComGithubCortexMcLibraryAccessors getMc() {
            return laccForComGithubCortexMcLibraryAccessors;
        }

    }

    public static class ComGithubCortexMcLibraryAccessors extends SubDependencyFactory {

        public ComGithubCortexMcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>simpapi</b> with <b>com.github.Cortex-MC:SimpAPI</b> coordinates and
         * with version reference <b>com.github.cortex.mc.simpapi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSimpapi() {
            return create("com.github.cortex.mc.simpapi");
        }

    }

    public static class ComGoogleLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeLibraryAccessors laccForComGoogleCodeLibraryAccessors = new ComGoogleCodeLibraryAccessors(owner);

        public ComGoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.google.code</b>
         */
        public ComGoogleCodeLibraryAccessors getCode() {
            return laccForComGoogleCodeLibraryAccessors;
        }

    }

    public static class ComGoogleCodeLibraryAccessors extends SubDependencyFactory {
        private final ComGoogleCodeGsonLibraryAccessors laccForComGoogleCodeGsonLibraryAccessors = new ComGoogleCodeGsonLibraryAccessors(owner);

        public ComGoogleCodeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.google.code.gson</b>
         */
        public ComGoogleCodeGsonLibraryAccessors getGson() {
            return laccForComGoogleCodeGsonLibraryAccessors;
        }

    }

    public static class ComGoogleCodeGsonLibraryAccessors extends SubDependencyFactory {

        public ComGoogleCodeGsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>gson</b> with <b>com.google.code.gson:gson</b> coordinates and
         * with version reference <b>com.google.code.gson.gson</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getGson() {
            return create("com.google.code.gson.gson");
        }

    }

    public static class ComOlziedevLibraryAccessors extends SubDependencyFactory {
        private final ComOlziedevPlayerwarpsLibraryAccessors laccForComOlziedevPlayerwarpsLibraryAccessors = new ComOlziedevPlayerwarpsLibraryAccessors(owner);

        public ComOlziedevLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.olziedev.playerwarps</b>
         */
        public ComOlziedevPlayerwarpsLibraryAccessors getPlayerwarps() {
            return laccForComOlziedevPlayerwarpsLibraryAccessors;
        }

    }

    public static class ComOlziedevPlayerwarpsLibraryAccessors extends SubDependencyFactory {

        public ComOlziedevPlayerwarpsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>com.olziedev:playerwarps-api</b> coordinates and
         * with version reference <b>com.olziedev.playerwarps.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("com.olziedev.playerwarps.api");
        }

    }

    public static class ComSk89qLibraryAccessors extends SubDependencyFactory {
        private final ComSk89qWorldguardLibraryAccessors laccForComSk89qWorldguardLibraryAccessors = new ComSk89qWorldguardLibraryAccessors(owner);

        public ComSk89qLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.sk89q.worldguard</b>
         */
        public ComSk89qWorldguardLibraryAccessors getWorldguard() {
            return laccForComSk89qWorldguardLibraryAccessors;
        }

    }

    public static class ComSk89qWorldguardLibraryAccessors extends SubDependencyFactory {
        private final ComSk89qWorldguardWorldguardLibraryAccessors laccForComSk89qWorldguardWorldguardLibraryAccessors = new ComSk89qWorldguardWorldguardLibraryAccessors(owner);

        public ComSk89qWorldguardLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>com.sk89q.worldguard.worldguard</b>
         */
        public ComSk89qWorldguardWorldguardLibraryAccessors getWorldguard() {
            return laccForComSk89qWorldguardWorldguardLibraryAccessors;
        }

    }

    public static class ComSk89qWorldguardWorldguardLibraryAccessors extends SubDependencyFactory {

        public ComSk89qWorldguardWorldguardLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>bukkit</b> with <b>com.sk89q.worldguard:worldguard-bukkit</b> coordinates and
         * with version reference <b>com.sk89q.worldguard.worldguard.bukkit</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBukkit() {
            return create("com.sk89q.worldguard.worldguard.bukkit");
        }

    }

    public static class IoLibraryAccessors extends SubDependencyFactory {
        private final IoPapermcLibraryAccessors laccForIoPapermcLibraryAccessors = new IoPapermcLibraryAccessors(owner);

        public IoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>io.papermc</b>
         */
        public IoPapermcLibraryAccessors getPapermc() {
            return laccForIoPapermcLibraryAccessors;
        }

    }

    public static class IoPapermcLibraryAccessors extends SubDependencyFactory {

        public IoPapermcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>paperlib</b> with <b>io.papermc:paperlib</b> coordinates and
         * with version reference <b>io.papermc.paperlib</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPaperlib() {
            return create("io.papermc.paperlib");
        }

    }

    public static class NetLibraryAccessors extends SubDependencyFactory {
        private final NetEssentialsxLibraryAccessors laccForNetEssentialsxLibraryAccessors = new NetEssentialsxLibraryAccessors(owner);
        private final NetKyoriLibraryAccessors laccForNetKyoriLibraryAccessors = new NetKyoriLibraryAccessors(owner);

        public NetLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>net.essentialsx</b>
         */
        public NetEssentialsxLibraryAccessors getEssentialsx() {
            return laccForNetEssentialsxLibraryAccessors;
        }

        /**
         * Group of libraries at <b>net.kyori</b>
         */
        public NetKyoriLibraryAccessors getKyori() {
            return laccForNetKyoriLibraryAccessors;
        }

    }

    public static class NetEssentialsxLibraryAccessors extends SubDependencyFactory {

        public NetEssentialsxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>essentialsx</b> with <b>net.essentialsx:EssentialsX</b> coordinates and
         * with version reference <b>net.essentialsx.essentialsx</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getEssentialsx() {
            return create("net.essentialsx.essentialsx");
        }

    }

    public static class NetKyoriLibraryAccessors extends SubDependencyFactory {
        private final NetKyoriAdventureLibraryAccessors laccForNetKyoriAdventureLibraryAccessors = new NetKyoriAdventureLibraryAccessors(owner);

        public NetKyoriLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>net.kyori.adventure</b>
         */
        public NetKyoriAdventureLibraryAccessors getAdventure() {
            return laccForNetKyoriAdventureLibraryAccessors;
        }

    }

    public static class NetKyoriAdventureLibraryAccessors extends SubDependencyFactory {

        public NetKyoriAdventureLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>net.kyori:adventure-api</b> coordinates and
         * with version reference <b>net.kyori.adventure.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("net.kyori.adventure.api");
        }

    }

    public static class OrgLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheLibraryAccessors laccForOrgApacheLibraryAccessors = new OrgApacheLibraryAccessors(owner);
        private final OrgJetbrainsLibraryAccessors laccForOrgJetbrainsLibraryAccessors = new OrgJetbrainsLibraryAccessors(owner);
        private final OrgMaxgamerLibraryAccessors laccForOrgMaxgamerLibraryAccessors = new OrgMaxgamerLibraryAccessors(owner);
        private final OrgProjectlombokLibraryAccessors laccForOrgProjectlombokLibraryAccessors = new OrgProjectlombokLibraryAccessors(owner);
        private final OrgSpigotmcLibraryAccessors laccForOrgSpigotmcLibraryAccessors = new OrgSpigotmcLibraryAccessors(owner);

        public OrgLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache</b>
         */
        public OrgApacheLibraryAccessors getApache() {
            return laccForOrgApacheLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.jetbrains</b>
         */
        public OrgJetbrainsLibraryAccessors getJetbrains() {
            return laccForOrgJetbrainsLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.maxgamer</b>
         */
        public OrgMaxgamerLibraryAccessors getMaxgamer() {
            return laccForOrgMaxgamerLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.projectlombok</b>
         */
        public OrgProjectlombokLibraryAccessors getProjectlombok() {
            return laccForOrgProjectlombokLibraryAccessors;
        }

        /**
         * Group of libraries at <b>org.spigotmc</b>
         */
        public OrgSpigotmcLibraryAccessors getSpigotmc() {
            return laccForOrgSpigotmcLibraryAccessors;
        }

    }

    public static class OrgApacheLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheCommonsLibraryAccessors laccForOrgApacheCommonsLibraryAccessors = new OrgApacheCommonsLibraryAccessors(owner);

        public OrgApacheLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.commons</b>
         */
        public OrgApacheCommonsLibraryAccessors getCommons() {
            return laccForOrgApacheCommonsLibraryAccessors;
        }

    }

    public static class OrgApacheCommonsLibraryAccessors extends SubDependencyFactory {
        private final OrgApacheCommonsCommonsLibraryAccessors laccForOrgApacheCommonsCommonsLibraryAccessors = new OrgApacheCommonsCommonsLibraryAccessors(owner);

        public OrgApacheCommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.apache.commons.commons</b>
         */
        public OrgApacheCommonsCommonsLibraryAccessors getCommons() {
            return laccForOrgApacheCommonsCommonsLibraryAccessors;
        }

    }

    public static class OrgApacheCommonsCommonsLibraryAccessors extends SubDependencyFactory {

        public OrgApacheCommonsCommonsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>lang3</b> with <b>org.apache.commons:commons-lang3</b> coordinates and
         * with version reference <b>org.apache.commons.commons.lang3</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLang3() {
            return create("org.apache.commons.commons.lang3");
        }

    }

    public static class OrgJetbrainsLibraryAccessors extends SubDependencyFactory {

        public OrgJetbrainsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>annotations</b> with <b>org.jetbrains:annotations</b> coordinates and
         * with version reference <b>org.jetbrains.annotations</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAnnotations() {
            return create("org.jetbrains.annotations");
        }

    }

    public static class OrgMaxgamerLibraryAccessors extends SubDependencyFactory {

        public OrgMaxgamerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>quickshop</b> with <b>org.maxgamer:QuickShop</b> coordinates and
         * with version reference <b>org.maxgamer.quickshop</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getQuickshop() {
            return create("org.maxgamer.quickshop");
        }

    }

    public static class OrgProjectlombokLibraryAccessors extends SubDependencyFactory {

        public OrgProjectlombokLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>lombok</b> with <b>org.projectlombok:lombok</b> coordinates and
         * with version reference <b>org.projectlombok.lombok</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLombok() {
            return create("org.projectlombok.lombok");
        }

    }

    public static class OrgSpigotmcLibraryAccessors extends SubDependencyFactory {
        private final OrgSpigotmcSpigotLibraryAccessors laccForOrgSpigotmcSpigotLibraryAccessors = new OrgSpigotmcSpigotLibraryAccessors(owner);

        public OrgSpigotmcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>org.spigotmc.spigot</b>
         */
        public OrgSpigotmcSpigotLibraryAccessors getSpigot() {
            return laccForOrgSpigotmcSpigotLibraryAccessors;
        }

    }

    public static class OrgSpigotmcSpigotLibraryAccessors extends SubDependencyFactory {

        public OrgSpigotmcSpigotLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.spigotmc:spigot-api</b> coordinates and
         * with version reference <b>org.spigotmc.spigot.api</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("org.spigotmc.spigot.api");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final CcVersionAccessors vaccForCcVersionAccessors = new CcVersionAccessors(providers, config);
        private final ComVersionAccessors vaccForComVersionAccessors = new ComVersionAccessors(providers, config);
        private final IoVersionAccessors vaccForIoVersionAccessors = new IoVersionAccessors(providers, config);
        private final NetVersionAccessors vaccForNetVersionAccessors = new NetVersionAccessors(providers, config);
        private final OrgVersionAccessors vaccForOrgVersionAccessors = new OrgVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.cc</b>
         */
        public CcVersionAccessors getCc() {
            return vaccForCcVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com</b>
         */
        public ComVersionAccessors getCom() {
            return vaccForComVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.io</b>
         */
        public IoVersionAccessors getIo() {
            return vaccForIoVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.net</b>
         */
        public NetVersionAccessors getNet() {
            return vaccForNetVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org</b>
         */
        public OrgVersionAccessors getOrg() {
            return vaccForOrgVersionAccessors;
        }

    }

    public static class CcVersionAccessors extends VersionFactory  {

        private final CcCarmVersionAccessors vaccForCcCarmVersionAccessors = new CcCarmVersionAccessors(providers, config);
        public CcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.cc.carm</b>
         */
        public CcCarmVersionAccessors getCarm() {
            return vaccForCcCarmVersionAccessors;
        }

    }

    public static class CcCarmVersionAccessors extends VersionFactory  {

        private final CcCarmLibVersionAccessors vaccForCcCarmLibVersionAccessors = new CcCarmLibVersionAccessors(providers, config);
        public CcCarmVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.cc.carm.lib</b>
         */
        public CcCarmLibVersionAccessors getLib() {
            return vaccForCcCarmLibVersionAccessors;
        }

    }

    public static class CcCarmLibVersionAccessors extends VersionFactory  {

        private final CcCarmLibEasysqlVersionAccessors vaccForCcCarmLibEasysqlVersionAccessors = new CcCarmLibEasysqlVersionAccessors(providers, config);
        public CcCarmLibVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.cc.carm.lib.easysql</b>
         */
        public CcCarmLibEasysqlVersionAccessors getEasysql() {
            return vaccForCcCarmLibEasysqlVersionAccessors;
        }

    }

    public static class CcCarmLibEasysqlVersionAccessors extends VersionFactory  {

        public CcCarmLibEasysqlVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>cc.carm.lib.easysql.api</b> with value <b>0.4.7</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("cc.carm.lib.easysql.api"); }

    }

    public static class ComVersionAccessors extends VersionFactory  {

        private final ComGhostchuVersionAccessors vaccForComGhostchuVersionAccessors = new ComGhostchuVersionAccessors(providers, config);
        private final ComGithubVersionAccessors vaccForComGithubVersionAccessors = new ComGithubVersionAccessors(providers, config);
        private final ComGoogleVersionAccessors vaccForComGoogleVersionAccessors = new ComGoogleVersionAccessors(providers, config);
        private final ComOlziedevVersionAccessors vaccForComOlziedevVersionAccessors = new ComOlziedevVersionAccessors(providers, config);
        private final ComSk89qVersionAccessors vaccForComSk89qVersionAccessors = new ComSk89qVersionAccessors(providers, config);
        public ComVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.ghostchu</b>
         */
        public ComGhostchuVersionAccessors getGhostchu() {
            return vaccForComGhostchuVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.github</b>
         */
        public ComGithubVersionAccessors getGithub() {
            return vaccForComGithubVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.google</b>
         */
        public ComGoogleVersionAccessors getGoogle() {
            return vaccForComGoogleVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.olziedev</b>
         */
        public ComOlziedevVersionAccessors getOlziedev() {
            return vaccForComOlziedevVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.com.sk89q</b>
         */
        public ComSk89qVersionAccessors getSk89q() {
            return vaccForComSk89qVersionAccessors;
        }

    }

    public static class ComGhostchuVersionAccessors extends VersionFactory  {

        private final ComGhostchuQuickshopVersionAccessors vaccForComGhostchuQuickshopVersionAccessors = new ComGhostchuQuickshopVersionAccessors(providers, config);
        public ComGhostchuVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.ghostchu.quickshop</b>
         */
        public ComGhostchuQuickshopVersionAccessors getQuickshop() {
            return vaccForComGhostchuQuickshopVersionAccessors;
        }

    }

    public static class ComGhostchuQuickshopVersionAccessors extends VersionFactory  {

        public ComGhostchuQuickshopVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.ghostchu.quickshop.api</b> with value <b>6.0.0.10</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("com.ghostchu.quickshop.api"); }

        /**
         * Version alias <b>com.ghostchu.quickshop.bukkit</b> with value <b>6.0.0.10</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getBukkit() { return getVersion("com.ghostchu.quickshop.bukkit"); }

    }

    public static class ComGithubVersionAccessors extends VersionFactory  {

        private final ComGithubCortexVersionAccessors vaccForComGithubCortexVersionAccessors = new ComGithubCortexVersionAccessors(providers, config);
        public ComGithubVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.github.cortex</b>
         */
        public ComGithubCortexVersionAccessors getCortex() {
            return vaccForComGithubCortexVersionAccessors;
        }

    }

    public static class ComGithubCortexVersionAccessors extends VersionFactory  {

        private final ComGithubCortexMcVersionAccessors vaccForComGithubCortexMcVersionAccessors = new ComGithubCortexMcVersionAccessors(providers, config);
        public ComGithubCortexVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.github.cortex.mc</b>
         */
        public ComGithubCortexMcVersionAccessors getMc() {
            return vaccForComGithubCortexMcVersionAccessors;
        }

    }

    public static class ComGithubCortexMcVersionAccessors extends VersionFactory  {

        public ComGithubCortexMcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.github.cortex.mc.simpapi</b> with value <b>4.3.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getSimpapi() { return getVersion("com.github.cortex.mc.simpapi"); }

    }

    public static class ComGoogleVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeVersionAccessors vaccForComGoogleCodeVersionAccessors = new ComGoogleCodeVersionAccessors(providers, config);
        public ComGoogleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.google.code</b>
         */
        public ComGoogleCodeVersionAccessors getCode() {
            return vaccForComGoogleCodeVersionAccessors;
        }

    }

    public static class ComGoogleCodeVersionAccessors extends VersionFactory  {

        private final ComGoogleCodeGsonVersionAccessors vaccForComGoogleCodeGsonVersionAccessors = new ComGoogleCodeGsonVersionAccessors(providers, config);
        public ComGoogleCodeVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.google.code.gson</b>
         */
        public ComGoogleCodeGsonVersionAccessors getGson() {
            return vaccForComGoogleCodeGsonVersionAccessors;
        }

    }

    public static class ComGoogleCodeGsonVersionAccessors extends VersionFactory  {

        public ComGoogleCodeGsonVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.google.code.gson.gson</b> with value <b>2.9.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getGson() { return getVersion("com.google.code.gson.gson"); }

    }

    public static class ComOlziedevVersionAccessors extends VersionFactory  {

        private final ComOlziedevPlayerwarpsVersionAccessors vaccForComOlziedevPlayerwarpsVersionAccessors = new ComOlziedevPlayerwarpsVersionAccessors(providers, config);
        public ComOlziedevVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.olziedev.playerwarps</b>
         */
        public ComOlziedevPlayerwarpsVersionAccessors getPlayerwarps() {
            return vaccForComOlziedevPlayerwarpsVersionAccessors;
        }

    }

    public static class ComOlziedevPlayerwarpsVersionAccessors extends VersionFactory  {

        public ComOlziedevPlayerwarpsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.olziedev.playerwarps.api</b> with value <b>6.30.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("com.olziedev.playerwarps.api"); }

    }

    public static class ComSk89qVersionAccessors extends VersionFactory  {

        private final ComSk89qWorldguardVersionAccessors vaccForComSk89qWorldguardVersionAccessors = new ComSk89qWorldguardVersionAccessors(providers, config);
        public ComSk89qVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.sk89q.worldguard</b>
         */
        public ComSk89qWorldguardVersionAccessors getWorldguard() {
            return vaccForComSk89qWorldguardVersionAccessors;
        }

    }

    public static class ComSk89qWorldguardVersionAccessors extends VersionFactory  {

        private final ComSk89qWorldguardWorldguardVersionAccessors vaccForComSk89qWorldguardWorldguardVersionAccessors = new ComSk89qWorldguardWorldguardVersionAccessors(providers, config);
        public ComSk89qWorldguardVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.com.sk89q.worldguard.worldguard</b>
         */
        public ComSk89qWorldguardWorldguardVersionAccessors getWorldguard() {
            return vaccForComSk89qWorldguardWorldguardVersionAccessors;
        }

    }

    public static class ComSk89qWorldguardWorldguardVersionAccessors extends VersionFactory  {

        public ComSk89qWorldguardWorldguardVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>com.sk89q.worldguard.worldguard.bukkit</b> with value <b>7.0.7</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getBukkit() { return getVersion("com.sk89q.worldguard.worldguard.bukkit"); }

    }

    public static class IoVersionAccessors extends VersionFactory  {

        private final IoPapermcVersionAccessors vaccForIoPapermcVersionAccessors = new IoPapermcVersionAccessors(providers, config);
        public IoVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.io.papermc</b>
         */
        public IoPapermcVersionAccessors getPapermc() {
            return vaccForIoPapermcVersionAccessors;
        }

    }

    public static class IoPapermcVersionAccessors extends VersionFactory  {

        public IoPapermcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>io.papermc.paperlib</b> with value <b>1.0.7</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getPaperlib() { return getVersion("io.papermc.paperlib"); }

    }

    public static class NetVersionAccessors extends VersionFactory  {

        private final NetEssentialsxVersionAccessors vaccForNetEssentialsxVersionAccessors = new NetEssentialsxVersionAccessors(providers, config);
        private final NetKyoriVersionAccessors vaccForNetKyoriVersionAccessors = new NetKyoriVersionAccessors(providers, config);
        public NetVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.net.essentialsx</b>
         */
        public NetEssentialsxVersionAccessors getEssentialsx() {
            return vaccForNetEssentialsxVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.net.kyori</b>
         */
        public NetKyoriVersionAccessors getKyori() {
            return vaccForNetKyoriVersionAccessors;
        }

    }

    public static class NetEssentialsxVersionAccessors extends VersionFactory  {

        public NetEssentialsxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>net.essentialsx.essentialsx</b> with value <b>2.19.4</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getEssentialsx() { return getVersion("net.essentialsx.essentialsx"); }

    }

    public static class NetKyoriVersionAccessors extends VersionFactory  {

        private final NetKyoriAdventureVersionAccessors vaccForNetKyoriAdventureVersionAccessors = new NetKyoriAdventureVersionAccessors(providers, config);
        public NetKyoriVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.net.kyori.adventure</b>
         */
        public NetKyoriAdventureVersionAccessors getAdventure() {
            return vaccForNetKyoriAdventureVersionAccessors;
        }

    }

    public static class NetKyoriAdventureVersionAccessors extends VersionFactory  {

        public NetKyoriAdventureVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>net.kyori.adventure.api</b> with value <b>4.14.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("net.kyori.adventure.api"); }

    }

    public static class OrgVersionAccessors extends VersionFactory  {

        private final OrgApacheVersionAccessors vaccForOrgApacheVersionAccessors = new OrgApacheVersionAccessors(providers, config);
        private final OrgJetbrainsVersionAccessors vaccForOrgJetbrainsVersionAccessors = new OrgJetbrainsVersionAccessors(providers, config);
        private final OrgMaxgamerVersionAccessors vaccForOrgMaxgamerVersionAccessors = new OrgMaxgamerVersionAccessors(providers, config);
        private final OrgProjectlombokVersionAccessors vaccForOrgProjectlombokVersionAccessors = new OrgProjectlombokVersionAccessors(providers, config);
        private final OrgSpigotmcVersionAccessors vaccForOrgSpigotmcVersionAccessors = new OrgSpigotmcVersionAccessors(providers, config);
        public OrgVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache</b>
         */
        public OrgApacheVersionAccessors getApache() {
            return vaccForOrgApacheVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.jetbrains</b>
         */
        public OrgJetbrainsVersionAccessors getJetbrains() {
            return vaccForOrgJetbrainsVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.maxgamer</b>
         */
        public OrgMaxgamerVersionAccessors getMaxgamer() {
            return vaccForOrgMaxgamerVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.projectlombok</b>
         */
        public OrgProjectlombokVersionAccessors getProjectlombok() {
            return vaccForOrgProjectlombokVersionAccessors;
        }

        /**
         * Group of versions at <b>versions.org.spigotmc</b>
         */
        public OrgSpigotmcVersionAccessors getSpigotmc() {
            return vaccForOrgSpigotmcVersionAccessors;
        }

    }

    public static class OrgApacheVersionAccessors extends VersionFactory  {

        private final OrgApacheCommonsVersionAccessors vaccForOrgApacheCommonsVersionAccessors = new OrgApacheCommonsVersionAccessors(providers, config);
        public OrgApacheVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.commons</b>
         */
        public OrgApacheCommonsVersionAccessors getCommons() {
            return vaccForOrgApacheCommonsVersionAccessors;
        }

    }

    public static class OrgApacheCommonsVersionAccessors extends VersionFactory  {

        private final OrgApacheCommonsCommonsVersionAccessors vaccForOrgApacheCommonsCommonsVersionAccessors = new OrgApacheCommonsCommonsVersionAccessors(providers, config);
        public OrgApacheCommonsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.apache.commons.commons</b>
         */
        public OrgApacheCommonsCommonsVersionAccessors getCommons() {
            return vaccForOrgApacheCommonsCommonsVersionAccessors;
        }

    }

    public static class OrgApacheCommonsCommonsVersionAccessors extends VersionFactory  {

        public OrgApacheCommonsCommonsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.apache.commons.commons.lang3</b> with value <b>3.12.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLang3() { return getVersion("org.apache.commons.commons.lang3"); }

    }

    public static class OrgJetbrainsVersionAccessors extends VersionFactory  {

        public OrgJetbrainsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.jetbrains.annotations</b> with value <b>23.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAnnotations() { return getVersion("org.jetbrains.annotations"); }

    }

    public static class OrgMaxgamerVersionAccessors extends VersionFactory  {

        public OrgMaxgamerVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.maxgamer.quickshop</b> with value <b>5.1.2.5</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getQuickshop() { return getVersion("org.maxgamer.quickshop"); }

    }

    public static class OrgProjectlombokVersionAccessors extends VersionFactory  {

        public OrgProjectlombokVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.projectlombok.lombok</b> with value <b>1.18.26</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getLombok() { return getVersion("org.projectlombok.lombok"); }

    }

    public static class OrgSpigotmcVersionAccessors extends VersionFactory  {

        private final OrgSpigotmcSpigotVersionAccessors vaccForOrgSpigotmcSpigotVersionAccessors = new OrgSpigotmcSpigotVersionAccessors(providers, config);
        public OrgSpigotmcVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Group of versions at <b>versions.org.spigotmc.spigot</b>
         */
        public OrgSpigotmcSpigotVersionAccessors getSpigot() {
            return vaccForOrgSpigotmcSpigotVersionAccessors;
        }

    }

    public static class OrgSpigotmcSpigotVersionAccessors extends VersionFactory  {

        public OrgSpigotmcSpigotVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>org.spigotmc.spigot.api</b> with value <b>1.20.2-R0.1-SNAPSHOT</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getApi() { return getVersion("org.spigotmc.spigot.api"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
