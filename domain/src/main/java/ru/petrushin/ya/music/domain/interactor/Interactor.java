package ru.petrushin.ya.music.domain.interactor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

public abstract class Interactor<ResultType, ParameterType> {
    private final CompositeSubscription subscription = new CompositeSubscription();
    public static final String JOB = "JOB";
    protected final Scheduler jobScheduler;
    public static final String UI = "UI";
    private final Scheduler uiScheduler;


    public Interactor(Scheduler jobScheduler, Scheduler uiScheduler) {
        this.jobScheduler = jobScheduler;
        this.uiScheduler = uiScheduler;
    }

    protected abstract Observable<ResultType> buildObservable(ParameterType parameter);

    /**
     * Executes the current use case.
     * @param parameter we can set param which will be used in repository
     * @param subscriber will be listen to the observable build
     * with {@link #buildObservable(Object)} ()}.
     */
    public void execute(ParameterType parameter, Subscriber<ResultType> subscriber) {
        subscription.add(buildObservable(parameter)
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber));
    }

    public void execute(Subscriber<ResultType> subscriber) {
        execute(null, subscriber);
    }

    /**
     * Unsubscribes from current {@link rx.subscriptions.CompositeSubscription}.
     */
    public void unsubscribe() {
        subscription.clear();
    }


}