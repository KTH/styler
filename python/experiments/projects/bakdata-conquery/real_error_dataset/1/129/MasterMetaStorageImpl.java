package com.bakdata.conquery.io.xodus;

import com.bakdata.conquery.io.xodus.stores.IdentifiableStore;
import com.bakdata.conquery.io.xodus.stores.KeyIncludingStore;
import com.bakdata.conquery.io.xodus.stores.SingletonStore;
import com.bakdata.conquery.models.auth.subjects.Mandator;
import com.bakdata.conquery.models.auth.subjects.User;
import com.bakdata.conquery.models.config.StorageConfig;
import com.bakdata.conquery.models.exceptions.JSONException;
import com.bakdata.conquery.models.execution.ManagedExecution;
import com.bakdata.conquery.models.identifiable.ids.specific.ManagedExecutionId;
import com.bakdata.conquery.models.identifiable.ids.specific.MandatorId;
import com.bakdata.conquery.models.identifiable.ids.specific.UserId;
import com.bakdata.conquery.models.worker.Namespaces;
import com.bakdata.conquery.util.functions.Collector;
import jetbrains.exodus.env.Environment;
import jetbrains.exodus.env.Environments;
import lombok.Getter;

import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class MasterMetaStorageImpl extends ConqueryStorageImpl implements MasterMetaStorage, ConqueryStorage {

	private SingletonStore<Namespaces> meta;
	private IdentifiableStore<ManagedExecution> executions;
	private IdentifiableStore<User> authUser;
	private IdentifiableStore<Mandator> authMandator;

	@Getter
	private Namespaces namespaces;

	@Getter
	private final Environment executionsEnvironment;

	@Getter
	private final Environment usersEnvironment;

	@Getter
	private final Environment mandatorEnvironment;

	public MasterMetaStorageImpl(Namespaces namespaces, Validator validator, StorageConfig config) {
		super(
				validator,
				config,
				new File(config.getDirectory(), "meta")
		);

		executionsEnvironment = Environments.newInstance(
				new File(config.getDirectory(), "executions"),
				config.getXodus().createConfig()
		);

		usersEnvironment = Environments.newInstance(
				new File(config.getDirectory(), "users"),
				config.getXodus().createConfig()
		);

		mandatorEnvironment = Environments.newInstance(
				new File(config.getDirectory(), "mandators"),
				config.getXodus().createConfig()
		);

		this.namespaces = namespaces;
	}

	@Override
	protected void createStores(Collector<KeyIncludingStore<?, ?>> collector) {

		meta = StoreInfo.NAMESPACES.singleton(getEnvironment(), getValidator());

		executions = StoreInfo.EXECUTIONS.<ManagedExecution>identifiable(getExecutionsEnvironment(), getValidator(), getCentralRegistry(), namespaces)
							 .onAdd(value -> value.initExecutable(namespaces.get(value.getDataset())));

		authMandator = StoreInfo.AUTH_MANDATOR.identifiable(getMandatorEnvironment(), getValidator(), getCentralRegistry());

		authUser = StoreInfo.AUTH_USER.identifiable(getUsersEnvironment(), getValidator(), getCentralRegistry());

		collector
				.collect(meta)
				.collect(authMandator)
				//load users before queries
				.collect(authUser)
				.collect(executions);
	}

	@Override
	public void addExecution(ManagedExecution query) throws JSONException {
		executions.add(query);
	}

	@Override
	public ManagedExecution getExecution(ManagedExecutionId id) {
		return executions.get(id);
	}

	@Override
	public Collection<ManagedExecution> getAllExecutions() {
		return executions.getAll();
	}

	@Override
	public void updateExecution(ManagedExecution query) throws JSONException {
		executions.update(query);
	}

	@Override
	public void removeExecution(ManagedExecutionId id) {
		executions.remove(id);
	}

	@Override
	public void addUser(User user) throws JSONException {
		authUser.add(user);
	}

	@Override
	public User getUser(UserId userId) {
		return authUser.get(userId);
	}

	@Override
	public Collection<User> getAllUsers() {
		return authUser.getAll();
	}

	@Override
	public void removeUser(UserId userId) {
		authUser.remove(userId);
	}

	@Override
	public void addMandator(Mandator mandator) throws JSONException {
		authMandator.add(mandator);
	}

	@Override
	public Mandator getMandator(MandatorId mandatorId) {
		return authMandator.get(mandatorId);
	}

	@Override
	public Collection<Mandator> getAllMandators() {
		return authMandator.getAll();
	}

	@Override
	public void removeMandator(MandatorId mandatorId) {
		authMandator.remove(mandatorId);
	}

	@Override
	public void updateUser(User user) throws JSONException {
		authUser.update(user);
	}

	@Override
	public void updateMandator(Mandator mandator) throws JSONException {
		authMandator.update(mandator);
	}

	@Override
	public void close() throws IOException {
		getUsersEnvironment().close();
		getExecutionsEnvironment().close();
		getMandatorEnvironment().close();

		super.close();
	}
}