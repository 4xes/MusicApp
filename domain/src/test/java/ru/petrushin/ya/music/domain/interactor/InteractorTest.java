package ru.petrushin.ya.music.domain.interactor;

import javax.inject.Named;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.Scheduler;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

public class InteractorTest {

  private InteractorTestClass useCase;

  @Mock private @Named("Job") Scheduler mockJobScheduler;
  @Mock private @Named("UI") Scheduler mockUIScheduler;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.useCase = new InteractorTestClass(mockJobScheduler, mockUIScheduler);
  }

  @Test @SuppressWarnings("unchecked") public void testBuildUseCaseObservableReturnCorrectResult() {
    TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
    TestScheduler testScheduler = new TestScheduler();
    given(mockUIScheduler).willReturn(testScheduler);

    useCase.execute(testSubscriber);

    assertThat(testSubscriber.getOnNextEvents().size(), is(0));
  }

  @Test public void testSubscriptionWhenExecutingUseCase() {
    TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();

    useCase.execute(testSubscriber);
    useCase.unsubscribe();

    assertThat(testSubscriber.isUnsubscribed(), is(true));
  }

  private static class InteractorTestClass extends Interactor<Integer, Void> {

    public InteractorTestClass(Scheduler jobScheduler, Scheduler uiScheduler) {
      super(jobScheduler, uiScheduler);
    }

    @Override protected Observable<Integer> buildObservable(Void parameter) {
      return Observable.empty();
    }
  }
}