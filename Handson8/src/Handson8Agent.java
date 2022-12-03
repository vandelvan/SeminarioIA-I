

import jade.core.Agent;

import jade.core.behaviours.OneShotBehaviour;

public class Handson8Agent extends Agent {

    @Override
    protected void setup() {
        System.out.println("Starting Hands On 8 Agent");
        addBehaviour(new SolveEquationnBehaviour());
    }

    private class SolveEquationnBehaviour extends OneShotBehaviour {

        public void action() {
            int populationSize = 30;
            double mutationRate = 0.001;
            double crossoverRate = 0.95;
            int elitismCount = 0;
            int chromosomeLength = 6;
            int target = 30;
            GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, mutationRate, crossoverRate, elitismCount,
                    target);
            Population population = ga.initPopulation(chromosomeLength);
            int generation = 1;

            ga.evalPopulation(population);

            while (!ga.isTerminationConditionMet(population)) {
                printGenerationData(generation, population);

                population = ga.crossoverPopulation(population);

                population = ga.mutatePopulation(population);

                ga.evalPopulation(population);

                generation++;
            }

            printGenerationData(generation, population);

            System.out.println("Found solution in " + generation + " generations");
            System.out.println("Best solution chromosome: " + population.getFittest(0).toString());
            System.out.println("Best solution equation: " + population.getFittest(0).equationToString());
        }

        public void printGenerationData(int generation, Population population) {
            double populationFitness = population.getPopulationFitness();
            System.out.println("Generation:" + generation);
            System.out.println("Population fitness: " + populationFitness);

            System.out.println("Chromosomes:");
            for (Individual individual : population.getIndividuals()) {
                double individualFitness = individual.getFitness();
                double proportionateFitness = (individualFitness * 100) / populationFitness;
                System.out.println(individual.toString() + " | Fitness: " + individual.getFitness()
                        + " | Proportionate fitness value: " + proportionateFitness + "%");
            }

            System.out.println();
        }

        public int onEnd() {
            System.out.println("Hands On 8 Agent "+getAID().getName()+"Terminating");
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}
