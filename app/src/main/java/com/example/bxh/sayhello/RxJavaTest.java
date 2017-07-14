package com.example.bxh.sayhello;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observers.Observers;
import rx.schedulers.Schedulers;

import static com.example.bxh.sayhello.Utils.isOnMainThread;


/**
 * Created by buxiaohui on 17-7-11.
 */

public class RxJavaTest {
    private final static Observable.Transformer schedulersTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
    static Observable.Transformer schedulersTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
    Observable<Integer> observable1 = Observable.just(1, 2, 3, 4);
    Observable<Integer> observable2 = Observable.just(4, 5, 6);
    Observable<Integer> observable3 = Observable.just(1, 2, 3, 4).map(new Func1<Integer, Integer>() {
        @Override
        public Integer call(Integer integer) {
            if (integer == 4) {
                //throw new RuntimeException("ops error observable3");
            }
            return integer;
        }
    });
    Observable<Integer> observable4 = Observable.just(5, 6, 7, 8).map(new Func1<Integer, Integer>() {
        @Override
        public Integer call(Integer integer) {
            if (integer == 6) {
                throw new RuntimeException("ops error observable4");
            }
            return integer;
        }
    });
    private Context mContext;
    private String[] arr1 = {"aaaaa", "bbbbb", "ccccc", "ddddd"};
    private String[] arr = {"11111", "22222", "33333", "44444"};
    private int[] arr2 = {1, 2, 3, 4};

    public static void test() {
        RxJavaTest rxJavaTest = new RxJavaTest();
        rxJavaTest.testJust();
        rxJavaTest.testJust1();
        rxJavaTest.testFrom();
        rxJavaTest.testZip();
        rxJavaTest.testMerge();
        rxJavaTest.testFilter();
        rxJavaTest.testConcat();
        rxJavaTest.testStartWith();
        rxJavaTest.testMergeDelayError();
        rxJavaTest.testMergeDelayError1();
        rxJavaTest.testMergeDelayError2();
    }

    public static <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

    private String[] getArrClone(String[] a) {
        return a.clone();
    }

    private String[] getArrClone() {
        return arr.clone();
    }

    public Observable<String[]> getString1() {
        return Observable.just(getArrClone());
    }

    public Observable<String[]> getString2() {
        return Observable.just(getArrClone(arr1));
    }

    public void testZip() {
        Log.i("RxJavaTest", "--------RxJavaTest testZip --------");
        Observable.zip(getString1(), getString2(), new Func2<String[], String[], ArrayList<String>>() {
            @Override
            public ArrayList<String> call(String[] strings, String[] strings2) {
                Log.i("RxJavaTest", "RxJavaTest testZip thread info=>" + isOnMainThread());
                ArrayList<String> list = new ArrayList<String>();
                int len = Math.max(strings.length, strings2.length);
                for (int i = 0; i < len; i++) {
                    list.add(strings[i] + strings2[i]);
                }
                return list;
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<ArrayList<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ArrayList<String> strings) {
                Log.i("RxJavaTest", "RxJavaTest testZip thread info=>" + isOnMainThread());
                Log.i("RxJavaTest", "RxJavaTest testZip strings=" + Arrays.toString(strings.toArray()));
            }
        });
    }

    /**
     * from 输入是list输出是list的item
     * just 输入是list输出还是list
     **/
    public void testFrom() {
        Log.i("RxJavaTest", "--------RxJavaTest testFrom --------");
        Observable.from(getArrClone()).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("RxJavaTest", "RxJavaTest testFrom thread info=>" + isOnMainThread());
                Log.i("RxJavaTest", "RxJavaTest testFrom s=" + s);
            }
        });
    }

    public void testJust() {
        Log.i("RxJavaTest", "--------RxJavaTest testJust --------");
        Observable.just(getArrClone()).map(new Func1<String[], String[]>() {
            @Override
            public String[] call(String[] strings) {
                for (int i = 0; i < strings.length; i++) {
                    strings[i] = "haha--" + strings[i];
                }
                Log.i("RxJavaTest", "RxJavaTest testJust thread info=>" + isOnMainThread());
                return strings;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<String[]>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String[] strings) {
                Log.i("RxJavaTest", "RxJavaTest testJust thread info=>" + isOnMainThread());
                Log.i("RxJavaTest", "RxJavaTest testJust strings=" + Arrays.toString(strings));
            }
        });
    }

    public void testJust1() {
        Log.i("RxJavaTest", "--------RxJavaTest testJust1 --------");
        Observable.just("1", "2", "3", "4", "5").map(new Func1<String, String>() {
            @Override
            public String call(String strings) {
                strings = "haha--" + strings;
                Log.i("RxJavaTest", "RxJavaTest testJust1 thread info=>" + isOnMainThread());
                return strings;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String strings) {
                Log.i("RxJavaTest", "RxJavaTest testJust1 thread info=>" + isOnMainThread());
                Log.i("RxJavaTest", "RxJavaTest testJust1 strings=" + (strings));
            }
        });
    }

    public void testFilter() {
        Log.i("RxJavaTest", "--------RxJavaTest testFilter --------");
        Observable.just(1, 2, 3, 4, 5).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer val) {
                return val < 3;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer val) {
                Log.i("RxJavaTest", "RxJavaTest onNext testFilter thread info=>" + isOnMainThread());
                Log.i("RxJavaTest", "RxJavaTest onNext testFilter strings=" + val);
            }
        });
    }

    /**
     * 而merge则是一遇到异常将停止发射数据，发送onError通知。
     */
    public void testMerge() {
        Log.i("RxJavaTest", "--------RxJavaTest testMerge --------");
        Observable.merge(observable1, observable2).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer strings) {
                return strings;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer strings) {
                Log.i("RxJavaTest", "RxJavaTest testMerge strings=" + strings);
            }
        });
    }

    /**
     * Even if both merged Observables send {@code onError} notifications, {@code mergeDelayError} will only
     * invoke the {@code onError} method of its Observers once.
     * 为什么没有效果?????
     */
    public void testMergeDelayError() {
        Log.i("RxJavaTest", "--------RxJavaTest testMergeDelayError --------");
        Observable.mergeDelayError(observable1, observable2).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                if (integer == 6) {
                    return 3 / 0;
                }
                return integer;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RxJavaTest", "RxJavaTest onError testMergeDelayError integer=" + e.toString());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("RxJavaTest", "RxJavaTest onNext testMergeDelayError integer=" + integer);
                    }
                });
    }

    public void testMergeDelayError1() {
        Log.i("RxJavaTest", "--------RxJavaTest testMergeDelayError1 --------");
        Observable.mergeDelayError(observable1.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                , observable2.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        if (integer == 4) {
                            //正常工作，直到抛出异常，后面不会发射了!!WHY????
                            throw new RuntimeException("ops error");
                        }
                        return integer;
                    }
                })
                //.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RxJavaTest", "RxJavaTest onError testMergeDelayError integer=" + e.toString());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("RxJavaTest", "RxJavaTest onNext testMergeDelayError integer=" + integer);
                    }
                });
    }

    public void testMergeDelayError2() {
        Log.i("RxJavaTest", "--------RxJavaTest testMergeDelayError2 --------");
        Observable.mergeDelayError(observable3.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                , observable4.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("RxJavaTest", "RxJavaTest onError testMergeDelayError2 integer=" + e.toString());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("RxJavaTest", "RxJavaTest onNext testMergeDelayError2 integer=" + integer);
                    }
                });
    }

    /**
     * 可以保持顺序
     */
    public void testConcat() {
        Log.i("RxJavaTest", "--------RxJavaTest testConcat --------");
        Observable.concat(observable1, observable2).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("RxJavaTest", "RxJavaTest onNext testConcat thread info=>" + isOnMainThread());
                Log.i("RxJavaTest", "RxJavaTest onNext testConcat strings=" + integer);
            }
        });
    }

    public void testCreate() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

            }
        }).subscribe(new Subscriber<Object>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void setProducer(Producer p) {
                super.setProducer(p);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
    }

    public void testStartWith() {
        Log.i("RxJavaTest", "--------RxJavaTest testStartWith --------");
        Observable.concat(observable1, observable2).startWith(6, 6, 6).observeOn(AndroidSchedulers.mainThread())
                .compose(schedulersTransformer)
                .compose(RxJavaTest.<Integer>applySchedulers())
                .subscribeOn(Schedulers.io()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("RxJavaTest", "RxJavaTest onNext testStartWith thread info=>" + isOnMainThread());
                Log.i("RxJavaTest", "RxJavaTest onNext testStartWith strings=" + integer);
            }
        });
    }
    AsyncTask asyncTask = new AsyncTask() {
        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Object o) {
            super.onCancelled(o);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    };
}
